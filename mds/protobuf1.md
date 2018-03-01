## protobuf自动批量导入的问题和细节。

[BatProtobuf]:https://github.com/dcl-Cheng/TestWebServer/tree/master/resource/proto/BatProtobuf.py

  自己写了一个批量导入的脚本[BatProtobuf]。运用于下面的规范。  
  这里主要是记录在这过程中踩的一些坑。主要是目录问题。
   
   
### 规范
  proto文件结构分明，模块管理。生成的文件结构和proto结构一致。只需管理proto文件。  
  拒绝所有文件都在一个目录和生成一个文件。这样没有可读性和可维护性。 
  

 
### 解析 

>*  **Java工具**：  原生的protoc  
>*  **C#工具**：    protobuf-net  protogen
    
>*  **Java解析方式**：  
  protoc --java_out=目标java包目录  --proto_path=proto包目录  proto文件
 
>*  **C#解析方式**：  
  protobuf-net\ProtoGen\protogen -i:proto文件 -o:cs文件   
  
  
### 要点
> * proto文件里的java_outpath记录了包路径。导入时会自动生成路径。
> * **--proto_path是proto包的路径，即proto的根目录。假设是root目录。****（重要）**
> * **proto文件里的import "..." 的路径就是root目录下的。** **（重要）** 
  
> * protobuf-net方式看的出，只能是单个文件解析为单个文件。 无法设置proto_path。   
> * **那么按Java一样导入，import的路径就会有问题。这里可能工具内置的规定。protogen所在位置得是root下。
    即root下一级同目录。批量导入目标根目录放在progen外层。**
    
### [BatProtobuf]参数说明
```python
import os

# 相关参数
text_suffix = ".proto"
source_dir_path = r"testPythonBatProto"

JavaKey = "Java"
CSKey = "CS"
languege = (JavaKey)

Java = {
    "type": JavaKey,
    "suffix": ".java",
    "compile_path": r"protoc",
    "target_path": r"G:\IntelliJSpace\MyIntelli\TestWebServer\resource\Tmp"
}
CS = {
    "type": CSKey,
    "suffix": ".cs",
    "compile_path": "F:\work\Client\pb\PBBuilder\protobuf-net\ProtoGen\protogen",
    "target_path": r"F:\work\Client\pb\PBBuilder\protobuf-net\rootCS"
}
```
**主要修改四个参数：**
>*   source_dir_path ：proto_path   即上面的说的proto包的根目录root。这里是testPythonBatProto
>*   compile_path:  protoc和protogen目录。 这里是protoc
>*   target_path：即生成后的根目录        这里是.....\Tmp
>*   languege： 选择生成谁，添加对应的key。 两个则填入（Javakey,CSKey）
     

### 源代码
   Java：[proto目录和小工具们](https://github.com/dcl-Cheng/TestWebServer/tree/master/resource/proto)
   和[生成的Java根目录](https://github.com/dcl-Cheng/TestWebServer/tree/master/Tmp)。     
   C#，先proto目录设置（把proto根目录下文件整到protogen目录，或把工具放过来和Java同工作区）。然后设置参数。  
   PS：脚本可以放任意地方额。上面参数设置就行。
   