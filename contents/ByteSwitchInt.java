//byte类型为一个字节，八位，
//int为32位。所以int与255得到最后八位的byte型数字
//然后通过位移，得到第二个、第三个和第四个八位的byte型数字，这四个byte型数字组成数组
//__需要注意的是：（byte）128&255的值为-128，而int i=-128&255，i的值为128.这是由于二进制最高位表示正负__
public class ByteSwitchInt {
	public  byte[] intToByte(int i)
	{
		byte[]b=new byte[4];
		for(int j=0;j<b.length;j++){
			b[j]=(byte)((i>>(j*8))&255);
		}
		return b;
	}
	public int byteToInt(byte[]b)
	{
		int i=0;
		for(int j=0;j<b.length;j++)
		{
			i+=(b[j]&255)<<(j*8);
		}
		return i;
	}
	public static void main(String[] args) {
		ByteSwitchInt t=new ByteSwitchInt();
		int i=12228;
		byte[]b=t.intToByte(i);
		i=t.byteToInt(b);
		System.out.println(i);
	}
}
