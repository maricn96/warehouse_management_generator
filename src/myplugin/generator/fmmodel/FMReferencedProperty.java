package myplugin.generator.fmmodel;

public class FMReferencedProperty extends FMProperty {
    
	private String cascade;
    private String fetch;
    private String mappedBy;
    private String joinTable;
    private String columnName;
    private Integer oppositeEnd;
    
    
	public FMReferencedProperty(String name, String type, String visibility, int lower, int upper, String cascade,
			String fetch, String mappedBy, String joinTable, String columnName, Integer oppositeEnd) {
		super(name, type, visibility, lower, upper);
		this.cascade = cascade;
		this.fetch = fetch;
		this.mappedBy = mappedBy;
		this.joinTable = joinTable;
		this.columnName = columnName;
		this.oppositeEnd = oppositeEnd;
	}

	public Integer getOppositeEnd() {
		return oppositeEnd;
	}



	public void setOppositeEnd(Integer oppositeEnd) {
		this.oppositeEnd = oppositeEnd;
	}



	public String getCascade() {
		return cascade;
	}


	public void setCascade(String cascade) {
		this.cascade = cascade;
	}


	public String getFetch() {
		return fetch;
	}


	public void setFetch(String fetch) {
		this.fetch = fetch;
	}


	public String getMappedBy() {
		return mappedBy;
	}


	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
	}


	public String getJoinTable() {
		return joinTable;
	}


	public void setJoinTable(String joinTable) {
		this.joinTable = joinTable;
	}


	public String getColumnName() {
		return columnName;
	}


	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

    
    
}

