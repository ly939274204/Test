package service;

import java.util.List;

import entity.Longurl;

public interface UrlService {
	String getUrl(String shorturl);

	List<String> getallUrl(String longurl);

	int ChangeUrl(Longurl longurl, String key);
}
