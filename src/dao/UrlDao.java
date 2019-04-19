package dao;

import java.util.List;

import entity.Longurl;

public interface UrlDao {
	String getUrl(String shorturl);

	List<String> getallUrl(String longurl);

	int insertlongurl(Longurl longurl);

	int insertshorturl(String shorturl, int u_id);
}
