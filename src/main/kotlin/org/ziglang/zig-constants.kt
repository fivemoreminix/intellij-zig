package org.ziglang

import org.jetbrains.annotations.NonNls

@NonNls
const val ZIG_NAME = "Zig"

@NonNls
const val ZIG_EXTENSION = "zig"

@NonNls
const val ZIG_WEBSITE = "https://ziglang.org"

@NonNls
const val ZIG_CONTEXT_ID = "ZIG_CONTEXT_ID"

@NonNls
const val ZIG_PLUGIN_ID = "org.ziglang"

@NonNls
const val ZIG_RUN_CONFIG_ID = "ZIG_RUN_CONFIG_ID"

@NonNls
const val ZIG_COMMENT_START = "//"

@JvmField
val builtinFunctions = listOf(
    "addWithOverflow",
    "alignCast",
    "alignOf",
    "as",
    "asyncCall",
    "atomicLoad",
    "atomicRmw",
    "atomicStore",
    "bitCast",
    "bitOffsetOf",
    "boolToInt",
    "bitSizeOf",
    "breakpoint",
    "mulAdd",
    "byteSwap",
    "bitReverse",
    "offsetOf",
    "call",
    "cDefine",
    "cImport",
    "cInclude",
    "clz",
    "cmpxchgStrong",
    "cmpxchgWeak",
    "compileError",
    "compileLog",
    "ctz",
    "cUndef",
    "divExact",
    "divFloor",
    "divTrunc",
    "embedFile",
    "enumToInt",
    "errorName",
    "errorReturnTrace",
    "errorToInt",
    "errSetCast",
    "export",
    "extern",
    "fence",
    "field",
    "fieldParentPtr",
    "floatCast",
    "floatToInt",
    "frame",
    "Frame",
    "frameAddress",
    "frameSize",
    "hasDecl",
    "hasField",
    "import",
    "intCast",
    "intToEnum",
    "intToError",
    "intToFloat",
    "intToPtr",
    "maximum",
    "memcpy",
    "memset",
    "minimum",
    "wasmMemorySize",
    "wasmMemoryGrow",
    "mod",
    "mulWithOverflow",
    "panic",
    "popCount",
    "prefetch",
    "ptrCast",
    "ptrToInt",
    "rem",
    "returnAddress",
    "select",
    "setAlignStack",
    "setCold",
    "setEvalBranchQuota",
    "setFloatMode",
    "setRuntimeSafety",
    "shlExact",
    "shlWithOverflow",
    "shrExact",
    "shuffle",
    "sizeOf",
    "splat",
    "reduce",
    "src",
    "sqrt",
    "sin",
    "cos",
    "exp",
    "exp2",
    "log",
    "log2",
    "log10",
    "fabs",
    "floor",
    "ceil",
    "trunc",
    "round",
    "subWithOverflow",
    "tagName",
    "This",
    "truncate",
    "Type",
    "typeInfo",
    "typeName",
    "TypeOf",
    "unionInit"
)
