# mjavac

A compiler for the [MiniJava](https://www.cambridge.org/resources/052182060X/) language.
To use this compiler, clone this project, run `mvn clean package`, and then run 

`java -jar mjavac-1.0-jar-with-dependencies.jar -o <outputdirectory> [-p] filename.mjava`

The option `-p` will allow to generate a prettified version of the input code in the output directory (Pretty Print Visitor).

### Example

```java
class Main {
    public static void main(String[] args) {
        System.out.println((new Test()).test());
    }
}

class Test {

    var a = 6;
    var b = 7;

    public int test() {
        return this.method(this.method(true, 13))[0];
    }

    public int[] method(boolean bool, int c) {
        int[] tobereturned = new int[3];
        tobereturned[0] = a;
        tobereturned[1] = b;
        tobereturned[2] = c;
        return tobereturned;
    }

    public int[] method(int[] array) {
        int i = 0;
        while (i < array.length) {
            System.out.println(array[i]);
            i = i + 1;
        }

        return array;
    }
}
```

This code will generate `Test.class`, as well as `Main.class`. Running `javap -c` on both of these files yields

#### `Main.class`
```
class Main {
  public Main();
    Code:
       0: aload_0
       1: invokespecial #27                 // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: getstatic     #19                 // Field java/lang/System.out:Ljava/io/PrintStream;
       3: new           #26                 // class Test
       6: dup
       7: invokespecial #4                  // Method Test."<init>":()V
      10: invokevirtual #13                 // Method Test.test:()I
      13: invokevirtual #12                 // Method java/io/PrintStream.println:(I)V
      16: return
}
```

#### `Test.class`
```
class Test {
  int a;

  int b;

  public Test();
    Code:
       0: aload_0
       1: invokespecial #39                 // Method java/lang/Object."<init>":()V
       4: aload_0
       5: ldc           #8                  // int 6
       7: putfield      #43                 // Field a:I
      10: aload_0
      11: ldc           #7                  // int 7
      13: putfield      #42                 // Field b:I
      16: return

  public int test();
    Code:
       0: goto          3
       3: goto          6
       6: aload_0
       7: aload_0
       8: ldc           #14                 // int 1
      10: ldc           #3                  // int 13
      12: invokevirtual #26                 // Method method:(BI)[I
      15: invokevirtual #38                 // Method method:([I)[I
      18: ldc           #15                 // int 0
      20: iaload
      21: ireturn

  public int[] method(byte, int);
    Code:
       0: ldc           #11                 // int 3
       2: newarray       int
       4: astore        3
       6: goto          9
       9: aload         3
      11: ldc           #15                 // int 0
      13: aload_0
      14: getfield      #43                 // Field a:I
      17: iastore
      18: aload         3
      20: ldc           #14                 // int 1
      22: aload_0
      23: getfield      #42                 // Field b:I
      26: iastore
      27: aload         3
      29: ldc           #13                 // int 2
      31: iload         2
      33: iastore
      34: goto          37
      37: aload         3
      39: areturn

  public int[] method(int[]);
    Code:
       0: ldc           #15                 // int 0
       2: istore        2
       4: goto          7
       7: iload         2
       9: aload         1
      11: arraylength
      12: if_icmplt     18
      15: goto          23
      18: ldc           #14                 // int 1
      20: goto          28
      23: ldc           #15                 // int 0
      25: goto          28
      28: ifeq          52
      31: getstatic     #41                 // Field java/lang/System.out:Ljava/io/PrintStream;
      34: aload         1
      36: iload         2
      38: iaload
      39: invokevirtual #34                 // Method java/io/PrintStream.println:(I)V
      42: iload         2
      44: ldc           #14                 // int 1
      46: iadd
      47: istore        2
      49: goto          7
      52: goto          55
      55: aload         1
      57: areturn
}
```

Running `java Main` prints 6, 7, 13 and 6 as expected.
