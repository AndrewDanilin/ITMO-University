public class Sum {
    public static void main(String[] args) {
        int result = 0;
        for (int i = 0 ; i < args.length; i++){
            result += sum(args[i]);
        }
        System.out.println(result);
    }

    public static int sum (String str){
        String[] newStr = str.split("[^0-9-]+");
        int sum = 0;
        for (String element:newStr) {
            if (element.equals(""))
                continue;
            sum += Integer.parseInt(element);
        }
        return(sum);
    }
}