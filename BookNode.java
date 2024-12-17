class BookNode {
		private String title;
		private String author;
		private BookNode next;

		public BookNode(String title, String author, Node next) {
			this.title = title;
			this.author = author;
			this.next = next;
		}

		public BookNode(String title, String author) {
			this(title, author, null);
		}
	} //end of BookNode class