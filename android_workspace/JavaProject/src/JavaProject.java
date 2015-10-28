


public class JavaProject {

	public String func9x9(){
		String result="";
		for (int i = 1;i < 10;i++){
			for (int j = 1;j < (i+1);j++){
				result += i + "*" + j + "=" + i*j + "\t";
			}
			result += "\n";
		}
		return result;
	}
	
	public String funcTree(){
		String result="     *\n    ***\n   *****\n";
		result += "     *\n    ***\n   *****\n  *******\n *********\n";
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JavaProject cfb = new JavaProject();
		System.out.println(cfb.func9x9());
		System.out.println(cfb.funcTree());
	}

}
