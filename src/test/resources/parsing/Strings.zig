const hello_world_in_c =
    \\#include <stdio.h>
    \\
    \\int main(int argc, char **argv) {
    \\    printf("hello world\n");
    \\    return 0;
    \\}
;

fn main() i32 {
	string = "Assisted Barrel Rolls\n";
	wife = "AkemiMadoka\n";
	character = 'a';

    std.debug.print(
      \\Hello world!
      \\
    , .{});

    var zig_exe_path = if (builtin.os.tag == .windows)
        \\What is the path to the 'zig' executable you would like to use?
        \\Note that due to a bug in zig (https://github.com/ziglang/zig/issues/6044),
        \\your zig directory cannot contain the '/' character.
    else
        "What is the path to the 'zig' executable you would like to use?";
}