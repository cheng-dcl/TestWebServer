// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: com/cheng/message/prototest/ProtoHead.proto

package com.cheng.message.prototest;

public final class ProtoHeadPro {
  private ProtoHeadPro() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public enum ProtoHead
      implements com.google.protobuf.ProtocolMessageEnum {
    LOGIN_REQ(0, 0),
    LOGIN_RESP(1, 1),
    GETINFO_REQ(2, 2),
    GETINFO_RESP(3, 3),
    ;
    
    public static final int LOGIN_REQ_VALUE = 0;
    public static final int LOGIN_RESP_VALUE = 1;
    public static final int GETINFO_REQ_VALUE = 2;
    public static final int GETINFO_RESP_VALUE = 3;
    
    
    public final int getNumber() { return value; }
    
    public static ProtoHead valueOf(int value) {
      switch (value) {
        case 0: return LOGIN_REQ;
        case 1: return LOGIN_RESP;
        case 2: return GETINFO_REQ;
        case 3: return GETINFO_RESP;
        default: return null;
      }
    }
    
    public static com.google.protobuf.Internal.EnumLiteMap<ProtoHead>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<ProtoHead>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<ProtoHead>() {
            public ProtoHead findValueByNumber(int number) {
              return ProtoHead.valueOf(number);
            }
          };
    
    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.cheng.message.prototest.ProtoHeadPro.getDescriptor().getEnumTypes().get(0);
    }
    
    private static final ProtoHead[] VALUES = {
      LOGIN_REQ, LOGIN_RESP, GETINFO_REQ, GETINFO_RESP, 
    };
    
    public static ProtoHead valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }
    
    private final int index;
    private final int value;
    
    private ProtoHead(int index, int value) {
      this.index = index;
      this.value = value;
    }
    
    // @@protoc_insertion_point(enum_scope:proto.ProtoHead)
  }
  
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n+com/cheng/message/prototest/ProtoHead." +
      "proto\022\005proto*M\n\tProtoHead\022\r\n\tLOGIN_REQ\020\000" +
      "\022\016\n\nLOGIN_RESP\020\001\022\017\n\013GETINFO_REQ\020\002\022\020\n\014GET" +
      "INFO_RESP\020\003B+\n\033com.cheng.message.protote" +
      "stB\014ProtoHeadPro"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }
  
  // @@protoc_insertion_point(outer_class_scope)
}
