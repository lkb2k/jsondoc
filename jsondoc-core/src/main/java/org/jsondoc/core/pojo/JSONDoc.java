package org.jsondoc.core.pojo;

import java.util.Map;
import java.util.Set;

public class JSONDoc {
	private String version;
	private String basePath;
	// The key is the group these apis belongs to. It can be empty.
	private Map<String, Set<ApiDoc>> apis;
	// The key is the group these objects belongs to. It can be empty.
	private Map<String, Set<ApiObjectDoc>> objects;

	public JSONDoc(String version, String basePath) {
		super();
		this.version = version;
		this.basePath = basePath;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Map<String, Set<ApiObjectDoc>> getObjects() {
		return objects;
	}

	public Map<String, Set<ApiDoc>> getApis() {
		return apis;
	}

	public void setApis(Map<String, Set<ApiDoc>> apis) {
		this.apis = apis;
	}

	public void setObjects(Map<String, Set<ApiObjectDoc>> objects) {
		this.objects = objects;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	@Override
	public String toString() {
		return "JSONDoc [version=" + version + ", basePath=" + basePath + ", apis=" + apis + ", objects=" + objects + "]";
	}

}
