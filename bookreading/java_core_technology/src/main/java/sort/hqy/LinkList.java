package sort.hqy;

public class LinkList {
	public Link first = null;
	public Link last = null;
	
	public LinkList(){}
	
	public void insertLast(int idata){
		Link link = new Link(idata);
		if(last == null){
			first =  link;
		}else{
			last.next = link;
		}	
		last = link;		
	}
	
	/**���û�����ͷ���-1 */
	public int popFirst(){		
		if(first != null){
			if(last == first){
				last = null;
			}
			int temp = first.idata;
			first = first.next;
			return temp;
		}
		return -1;
	}
	
	public void clear(){
		first = null;
		last = null;
	}
	public void print(){
		Link temp = first;
		while(temp!= null){
			System.out.print(temp.idata +  "  ");
			temp = temp.next;
		}
	}
}
