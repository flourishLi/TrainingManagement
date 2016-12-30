package com.trainingmanagement.service.util;

public class ByteConvertion {

	 public static byte[] int2Byte(int intValue) {
	        byte[] b = new byte[4];
	        for (int i = 0; i < 4; i++) {
	            b[i] = (byte) (intValue >> 8 * (3 - i) & 0xFF);
	            //System.out.print(Integer.toBinaryString(b[i])+" ");
	            //System.out.print((b[i] & 0xFF) + " ");
	        }
	        return b;
	    }

	    public static int byte2Int(byte[] b) {
	        int intValue = 0;
	        for (int i = 0; i < b.length; i++) {
	            intValue += (b[i] & 0xFF) << (8 * (3 - i));
	        }
	        return intValue;
	    }
	    
	    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2){  
	        byte[] byte_3 = new byte[byte_1.length+byte_2.length];  
	        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);  
	        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);  
	        return byte_3;  
	    }  
	    
	    public static int[] ByteToIntArray(byte[] btArray)  
	    {  
	        if (btArray.length % 4 != 0 && btArray.length > 0)  
	        {  
	            return null;  
	        }  
	        int nIntLen = btArray.length / 4;  
	        if (nIntLen * 4 != btArray.length)  
	        {  
	            return null;  
	        }  
	        int[] nTemp = new int[nIntLen];  
	        for (int i = 0; i < nIntLen; i++)  
	        {  
	            byte[] btTemp = new byte[4];  
	            btTemp[0] = btArray[i * 4];  
	            btTemp[1] = btArray[i * 4 + 1];  
	            btTemp[2] = btArray[i * 4 + 2];  
	            btTemp[3] = btArray[i * 4 + 3];  
	            nTemp[i] = byte2Int(btTemp);  
	        }  
	        return nTemp;  
	    }  
}
