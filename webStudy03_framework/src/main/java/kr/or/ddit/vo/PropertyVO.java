package kr.or.ddit.vo;

import java.io.Serializable;


/**
 * Domain Layer : model data, 정보의 속성과 의미 부여 및 영역 제한 
 *
 */
public class PropertyVO implements Serializable{

	private String property_name;
	private String property_value;
	private String description;
	
	private PropertyVO() {
		super();
	}

	private PropertyVO(String propertyName, String propertyValue, String description) {
		super();
		this.property_name = propertyName;
		this.property_value = propertyValue;
		this.description = description;
	}
	
	public String getProperty_name() {
		return property_name;
	}

	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}

	public String getProperty_value() {
		return property_value;
	}

	public void setProperty_value(String property_value) {
		this.property_value = property_value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "propertyVO [property_name=" + property_name + ", property_value=" + property_value + ", description="
				+ description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((property_name == null) ? 0 : property_name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PropertyVO other = (PropertyVO) obj;
		if (property_name == null) {
			if (other.property_name != null)
				return false;
		} else if (!property_name.equals(other.property_name))
			return false;
		return true;
	}

	public static class PropertyVOBuilder{
		
		private String property_name;
		private String property_value;
		private String description;
		
		public PropertyVOBuilder property_name(String property_name) {
			this.property_name = property_name;
			return this;
		}
		public PropertyVOBuilder property_value(String property_value) {
			this.property_value = property_value;
			return this;
		}
		public PropertyVOBuilder description(String description) {
			this.description = description;
			return this;
		}
		public PropertyVO build() {
			return new PropertyVO(property_name, property_value, description);
		}
	}
	
	public static PropertyVOBuilder getBuilder() {
		return new PropertyVOBuilder();
	}
	
}
