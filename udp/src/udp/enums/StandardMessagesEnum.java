package udp.enums;

public enum StandardMessagesEnum {

	OTIMO(1, "Ótimo - Estoque do berço acima de 85%"),
	BOM(2, "Bom - Estoque do berço acima de 70%"),
	REGULAR(3, "Regular - Estoque do berço acima de 50%"),
	RUIM(4, "Ruim - Estoque do berço abaixo de 50%"),
	PREOCUPANTE(5, "Estoque do berço abaixo de 30%"),
	CRÍTICO(6, "Estoque do berço abaixo de 10%");
	
	private int id;
	private String description;

	StandardMessagesEnum(int id, String description) {
		this.id = id;
		this.setDescription(description);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
