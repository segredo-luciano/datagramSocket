package vo;

public class NestVO {
	private Long register;
	private String name;
	private String woodType;
	private Float woodDensity;
	private Float capacity;
	private Float currentWeight;
	
	public Long getRegister() {
		return register;
	}
	public void setRegister(Long register) {
		this.register = register;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWoodType() {
		return woodType;
	}
	public void setWoodType(String woodType) {
		this.woodType = woodType;
	}
	public Float getWoodDensity() {
		return woodDensity;
	}
	public void setWoodDensity(Float woodDensity) {
		this.woodDensity = woodDensity;
	}
	public Float getCapacity() {
		return capacity;
	}
	public void setCapacity(Float capacity) {
		this.capacity = capacity;
	}
	public Float getCurrentWeight() {
		return currentWeight;
	}
	public void setCurrentWeight(Float currentWeight) {
		this.currentWeight = currentWeight;
	}
}
