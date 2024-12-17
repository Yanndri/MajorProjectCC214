class Node {

		private int item;
		private Node next;

		public Node(int item, Node next) {
			this.item = item;
			this.next = next;
		}

		public Node(int item) {
			this(item, null);
		}
	} //end of Node class