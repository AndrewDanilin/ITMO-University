public class SumLong {
    public static void main(String[] args) {
        long result = 0;
	for (int i = 0 ; i < args.length; i++){
		result += sum(args[i]);
	}
        System.out.println(result);
    }

    public static long sum(String newStr) {
        StringBuilder sb = new StringBuilder();
	String str = newStr + " ";
        long sum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                continue;
            } else if (Character.isWhitespace(str.charAt(i + 1))) {
                sb.append(str.charAt(i));
                sum += Long.parseLong(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sum;
    }
}