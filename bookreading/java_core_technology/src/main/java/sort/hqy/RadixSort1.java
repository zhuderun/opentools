package sort.hqy;

public class RadixSort1 {
	private static LinkList[] linkList = new LinkList[10];
	static {
		for (int i = 0; i < 10; i++) {
			linkList[i] = new LinkList();
		}
	}

	public static void clear() {
		for (int i = 0; i < 10; i++) {
			linkList[i].clear();
		}
	}

	public static void sort(int[] number) {
		int len = number.length;
		int[] sorted = new int[len];
		int num = len;
		int d = 1;
		int temp;
		int num1 = num;
		int t = 0;
		while (num > 0) {
			for (int i = 0; i < num1; i++) {
				temp = number[i] / d;
				if (temp == 0) {
					sorted[len - num] = number[i];
					num--;
				} else {
					temp = temp % 10;
					linkList[temp].insertLast(number[i]);
				}
			}
			num1 = num;
			for (int k = 0; k < 10; k++) {
				while ((temp = linkList[k].popFirst()) != -1) {
					number[t++] = temp;
				}
			}
			print(number, len);
			d = d * 10;
			t = 0;
		}
		clear();
		print(sorted, len);
		number = sorted;
		print(number, len);
	}

	public static void print(int[] number, int len) {
		System.out.println();
		for (int i = 0; i < len; i++) {
			System.out.print(number[i] + "  ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] data = { 73, 22, 93, 43, 55, 14, 28, 65, 39, 81, 33, 100 };
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		RadixSort1.sort(data);
	}

}
