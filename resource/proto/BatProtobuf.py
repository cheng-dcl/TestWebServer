#!/usr/bin/env.python
# _*_ coding:utf-8 _*_

"""
    批量导入proto文件生成相关文件，java,cs
    按规范制定proto包和对应的java包。可自动化解析
    File: BatProtobuf.py
    Author: dcl-Cheng   
"""

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
    #"compile_path": r"G:\IntelliJSpace\MyIntelli\TestWebServer\resource\proto\protoc",
    "compile_path": r"protoc",

    #"target_path": r"F:\work\Client\pb\PBBuilder\protobuf-net\ProtoGen\java"
    "target_path": r"G:\IntelliJSpace\MyIntelli\TestWebServer\resource\Tmp"
}
CS = {
    "type": CSKey,
    "suffix": ".cs",
    "compile_path": "F:\work\Client\pb\PBBuilder\protobuf-net\ProtoGen\protogen",
    "target_path": r"F:\work\Client\pb\PBBuilder\protobuf-net\ProtoGen\csharp"
}

filterPackge = ()  # TODO 过滤掉不需要的包名， 这段按规范来，后面该移除

listFile = []


# 检测目的根目录是否存在，否则创建.
def checkDir():
    if not os.path.exists(Java.get("target_path")):
        makeDir(Java.get("target_path"))
    if not os.path.exists(CS.get("target_path")):
        makeDir(CS.get("target_path"))


# 只删除根目录下的所有的文件,目录还是保留，防止下次再次重建目录
def delFiles(path):
    fileList = os.listdir(path)
    for f in fileList:
        filePath = os.path.join(path, f)
        if os.path.isfile(filePath):
            print("[Remove File]  " + filePath)
            os.remove(filePath)
        else:
            delFiles(filePath)


# 创建目录
def makeDir(path):
    if not os.path.exists(path):
        os.mkdir(path)


# 检索源文件并保存
def listFiles(filePath):
    dir = os.listdir(filePath)
    for s in dir:
        newPath = os.path.join(filePath, s)
        notProto = newPath

        if len(filterPackge) > 0:
            for p in filterPackge:
                if p in newPath:
                    notProto = notProto.replace("proto", "")

        if os.path.isfile(newPath):
            if os.path.splitext(newPath)[1] == text_suffix:
                print("[File]  ", newPath)
                listFile.append(newPath)
        else:
            if JavaKey in languege:
                #makeDir(os.path.join(Java.get("target_path"), splitDir(source_dir_path, notProto)))
                print()
            if CSKey in languege:
                makeDir(os.path.join(CS.get("target_path"), splitDir(source_dir_path, notProto)))

            listFiles(newPath)


# 切割目录
def splitDir(source, path):
    return path[len(source) + 1:]


# 编译
def compile(type):
    for file in listFile:
        exec(type, file)
    print("[Status] Protobuf Complier Success !!!")


# 编译具体
def exec(type, source):
    newSource = source[len(source_dir_path) + 1:]
    fronSource = os.path.split(newSource)[0]
    if type == JavaKey:
        # java 指定最终java包目录 ，prop文件包目录，prop文件目录

        compileStr = Java.get("compile_path") + \
                     " --java_out=" + Java.get("target_path") + " " \
                                                                " --proto_path=" + source_dir_path + " " + source
        print("JAVA : " + compileStr)
        os.system(compileStr)
    elif type == CSKey:
        # c# protobuf-net 只能编译当前目录下层的，上层的编译不行？？
        # -i：proto文件目录  -o：cs文件目录

        file = os.path.splitext(os.path.split(source)[1])[0] + CS.get("suffix")
        compileStr = CS.get("compile_path") + \
                     " -i:" + source + \
                     " -o:" + os.path.join(CS.get("target_path"), fronSource, file)
        print("CS : " + compileStr)
        os.system(compileStr)
    else:
        print("[error] No Type = ", type)


# 启动
def Do(language):
    checkDir()
    listFiles(source_dir_path)
    if JavaKey in languege:
        delFiles(Java.get("target_path"))
        compile(JavaKey)
    if CSKey in languege:
        delFiles(CS.get("target_path"))
        compile(CSKey)


Do(languege)
