package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndktools.javamd5.core.MD5;

import dao.UrlDao;
import entity.Longurl;
import service.UrlService;

@Service("urlService")
public class UrlServiceImpl implements UrlService{

	@Autowired
	private UrlDao urlDao;
	@Override
	public String getUrl(String shorturl) {
		String longurl = urlDao.getUrl(shorturl);
		return longurl;
	}

	@Override
	public int ChangeUrl(Longurl longurl, String key) {
		if (key!=null) {
			key = "wuguowei" ;
		}
		int row = 0;
		urlDao.insertlongurl(longurl);
	       // 要使用生成 URL 的字符
		String[] chars = new String[] { "a" , "b" , "c" , "d" , "e" , "f" , "g" , "h" ,
		   "i" , "j" , "k" , "l" , "m" , "n" , "o" , "p" , "q" , "r" , "s" , "t" ,
		   "u" , "v" , "w" , "x" , "y" , "z" , "0" , "1" , "2" , "3" , "4" , "5" ,
		   "6" , "7" , "8" , "9" , "A" , "B" , "C" , "D" , "E" , "F" , "G" , "H" ,
		   "I" , "J" , "K" , "L" , "M" , "N" , "O" , "P" , "Q" , "R" , "S" , "T" ,
		   "U" , "V" , "W" , "X" , "Y" , "Z"
		  };
		 
		  // 对传入网址进行 MD5 加密
		 
	     String sMD5EncryptResult =new MD5().getMD5ofStr(key + longurl);
		 String hex = sMD5EncryptResult;
		 String[] resUrl = new String[4];
		 for ( int i = 0; i < 4; i++) {
		   // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
	          String sTempSubString = hex.substring(i * 8, i * 8 + 8);
		  // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界
		  long lHexLong = 0x3FFFFFFF & Long.parseLong (sTempSubString, 16);
		  String outChars = "" ;
		  for ( int j = 0; j < 6; j++) {
		    // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
		     long index = 0x0000003D & lHexLong;
		    // 把取得的字符相加
		    outChars += chars[( int ) index];
		   // 每次循环按位右移 5 位
		    lHexLong = lHexLong >> 5;
		  }
		 row = urlDao.insertshorturl(outChars, longurl.getU_id());
		  // 把字符串存入对应索引的输出数组
		    resUrl[i] = outChars;
		 }
		return row;
	}

	@Override
	public List<String> getallUrl(String longurl) {
		return urlDao.getallUrl(longurl);
	}

}
