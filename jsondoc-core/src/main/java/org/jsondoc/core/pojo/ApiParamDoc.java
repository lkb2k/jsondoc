package org.jsondoc.core.pojo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jsondoc.core.annotation.ApiParam;
import org.jsondoc.core.util.JSONDocType;
import org.jsondoc.core.util.JSONDocTypeBuilder;

public class ApiParamDoc {
	public String jsondocId = UUID.randomUUID().toString();
	private JSONDocType jsondocType;
	private String name;
	private String description;
	private String required;
	private String[] allowedvalues;
	private String format;

	public ApiParamDoc(String name, String description, JSONDocType jsondocType, String required, String[] allowedvalues, String format) {
		super();
		this.name = name;
		this.description = description;
		this.jsondocType = jsondocType;
		this.required = required;
		this.allowedvalues = allowedvalues;
		this.format = format;
	}

	public static List<ApiParamDoc> getApiParamDocs(Method method, ApiParamType paramType) {
		List<ApiParamDoc> docs = new ArrayList<ApiParamDoc>();
		Annotation[][] parametersAnnotations = method.getParameterAnnotations();
		for (int i = 0; i < parametersAnnotations.length; i++) {
			for (int j = 0; j < parametersAnnotations[i].length; j++) {
				if (parametersAnnotations[i][j] instanceof ApiParam) {
					ApiParamDoc apiParamDoc = buildFromAnnotation((ApiParam) parametersAnnotations[i][j], getJSONDocType(method, i), paramType);
					if (apiParamDoc != null) {
						docs.add(apiParamDoc);
					}
				}
			}
		}

		return docs;
	}

	private static JSONDocType getJSONDocType(Method method, Integer index) {
		Class<?> type = method.getParameterTypes()[index];
		Type genericType = method.getGenericParameterTypes()[index];
		return JSONDocTypeBuilder.reflex(new JSONDocType(), type, genericType);
	}

	public static ApiParamDoc buildFromAnnotation(ApiParam annotation, JSONDocType jsondocType, ApiParamType paramType) {
		if (annotation.paramType().equals(paramType)) {
			return new ApiParamDoc(annotation.name(), annotation.description(), jsondocType, String.valueOf(annotation.required()), annotation.allowedvalues(), annotation.format());
		}
		return null;
	}

	public ApiParamDoc() {
		super();
	}

	public JSONDocType getJsondocType() {
		return jsondocType;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getRequired() {
		return required;
	}

	public String[] getAllowedvalues() {
		return allowedvalues;
	}

	public String getFormat() {
		return format;
	}

}
