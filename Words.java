import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class Main {
  static public boolean wordBreak(HashSet<String> words,String target){
    List<String> list=new ArrayList<>(); // leetcode
    list.add(target);
    List<String> tempList=new ArrayList<>();
    while(!list.isEmpty()){
      System.out.println(list);
      for(int i=0;i<list.size();i++){
        String s=list.get(i); // leetcode
        for(int j=0;j<s.length();j++){ // l , le, lee , leet,leetc,...
          String ss=s.substring(0,j+1); //code,tcode
          if(words.contains(ss)){
            tempList.add(s.substring(j+2));
            if(s.substring(j+1).equals("")) return true;
          }
        }
        if(i==list.size()-1){
          list=tempList;
          tempList=new ArrayList<>();
        }
      }
    }
    return false;
  }
  static public void main( String args[] ) {
    HashSet<String> words1=new HashSet<>(Arrays.asList(new String[]{"leet", "code"}));
    String target1="leetcode";
    System.out.println(wordBreak(words1,target1));
    System.out.println( "Practice makes Perfect!" );
  }
}