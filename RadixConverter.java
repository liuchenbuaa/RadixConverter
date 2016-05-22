public class RadixConverter {

    //ordered by ASCII alphabet, from 33 to 126, 94 total
    private static String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!\"#$%'()*+,-./:;=?@[\\]^_`{|}~";

    public static String convert (String inputNumber,int radixInput,int radixOutput){
        StringBuilder stringBuilder = new StringBuilder();
        String dividedNum = inputNumber;
        while(!dividedNum.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            int last = 0;
            for (int i = 0; i < dividedNum.length(); i++) {
                int currentNum = alphabet.indexOf(dividedNum.charAt(i));
                int result = (last * radixInput + currentNum)/radixOutput;
                last = (last * radixInput + currentNum)%radixOutput;
                sb.append(alphabet.charAt(result));
            }
            dividedNum = sb.toString().replaceFirst("^0+","");
            stringBuilder.append(alphabet.charAt(last));
        }
        return stringBuilder.reverse().toString();
    }

    public static void main(String[] args){
        for (int i = 0 ; i < 100 ;i++){
            String s = UUID.randomUUID().toString().replace("-","");
            String result = convert(s, 16, 94);
            System.out.print(result);
            boolean b = s.equals(convert(result,94,16));
            assert(b);
            System.out.println(" " + "done!");
        }

    }

}

