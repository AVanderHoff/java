package petshop;

public class Pet {

	private int id;
	private String animal;
	private int price;
	private int qty;

	public Pet(int id, String animal, int price, int qty) {
		super();
		this.id = id;
		this.animal = animal;
		this.price = price;
		this.qty = qty;
	}

	public Pet(String animal, int price, int qty) {
		super();
		this.animal = animal;
		this.price = price;
		this.qty = qty;
	}

	public Pet() {
		super();
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", animal=" + animal + ", price=" + price + ", qty=" + qty + "]";
	}

}
