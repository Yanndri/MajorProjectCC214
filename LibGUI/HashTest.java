package LibGUI;

import DataStructures.Node;
import Objects.User;

public class HashTest {
	Node[] items;

	public HashTest(int size) {
		items = new Node[size];
	}

	public HashTest() {
		this(10);
	}

	private int hash(int item) {
		return item % items.length;
	}

	public void insert(User user) {
		int i = hash(user.getKey());

		Node currNode = items[i];

		if (items[i] == null) {
			items[i] = new Node(user);
		} else {
			while (currNode.getLink() != null) {
				currNode = currNode.getLink();
			}
			currNode.setLink(new Node(user));
		}
	}

	public User getUser(int key) {
		int i = hash(key);
		Node currNode = items[i];
		while (currNode != null) {
			User user = (User) currNode.getItem();
			if (user.getKey() == key) {
				System.out.println("User found: " + user.getFirstName() + " with key: " + key); // Debug statement
				return user;
			}
			currNode = currNode.getLink();
		}
		System.out.println("User not found for key: " + key); // Debug statement
		return null;
	}
	

	public boolean search(User user) {
		boolean found = false;
		int i = hash(user.getKey());
		Node currNode = items[i];

		while (currNode != null) {
			if (currNode.getItem() == user) {
				found = true;
				break;
			}
			currNode = currNode.getLink();
		}
		return found;
	}

	public void display() {
		for (int i = 0; i < items.length; i++) {
			Node currNode = items[i];
			System.out.print("item[" + i + "] = ");
			System.out.print("{");
			while (currNode != null) {

				System.out.print(currNode.getItem() + " -> ");
				currNode = currNode.getLink();
			}
			System.out.println("null}");

		}
	}

	public void delete(User user) {
		int i = hash(user.getKey());
		Node currNode = items[i];
		if (currNode != null && currNode.getItem() == user) {
			currNode = currNode.getLink();
			return;
		}
		while (currNode != null && currNode.getLink().getItem() != user) {
			currNode = currNode.getLink();
		}
		if (currNode != null && currNode.getLink().getItem() == user)
			currNode.setLink(currNode.getLink().getLink());
		else
			System.out.println("Item not found");
	}

	// public static void main(String[] args) { // main
	// HashTest chain = new HashTest(10);
	// chain.insert(10);
	// chain.insert(8);
	// chain.insert(18);
	// chain.insert(11);
	// chain.insert(20);
	// chain.insert(25);
	// chain.insert(15);
	// chain.insert(28);
	// chain.insert(50);
	// chain.insert(60);
	// chain.insert(21);

	// chain.display();
	// System.out.println(chain.search(10));
	// System.out.println(chain.search(20));
	// System.out.println(chain.search(12));
	// chain.delete(50); //del
	// chain.insert(35);

	// System.out.println("\n");
	// chain.delete(11); //del
	// chain.display();

	// System.out.println(chain.search(10));
	// System.out.println();
	// chain.delete(35); //del
	// chain.display();
	// System.out.println(chain.search(60));

	// System.out.println();
	// chain.delete(22);
	// System.out.println(chain.search(28));
	// System.out.println(chain.search(28));
	// chain.display();
}
