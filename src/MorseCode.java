import java.util.TreeMap;

/**
   morse code
   @author  Audrey
   @version 2/11

   @author Period - 5
   @author Assignment - 

   @author Sources -
 */
public class MorseCode
{
    private static final char DOT = '.';
    private static final char DASH = '-';

    private static TreeMap<Character, String> codeMap;
    private static TreeNode<Character> decodeTree;

    public static void start()
    {
        codeMap = new TreeMap<Character, String>();

        // put a space in the root of the decoding tree
        decodeTree = new TreeNode<Character>(' ', null, null);  // autoboxing

        addSymbol('A', ".-");
        addSymbol('B', "-...");
        addSymbol('C', "-.-.");
        addSymbol('D', "-..");
        addSymbol('E', ".");
        addSymbol('F', "..-.");
        addSymbol('G', "--.");
        addSymbol('H', "....");
        addSymbol('I', "..");
        addSymbol('J', ".---");
        addSymbol('K', "-.-");
        addSymbol('L', ".-..");
        addSymbol('M', "--");
        addSymbol('N', "-.");
        addSymbol('O', "---");
        addSymbol('P', ".--.");
        addSymbol('Q', "--.-");
        addSymbol('R', ".-.");
        addSymbol('S', "...");
        addSymbol('T', "-");
        addSymbol('U', "..-");
        addSymbol('V', "...-");
        addSymbol('W', ".--");
        addSymbol('X', "-..-");
        addSymbol('Y', "-.--");
        addSymbol('Z', "--..");
        addSymbol('0', "-----");
        addSymbol('1', ".----");
        addSymbol('2', "..---");
        addSymbol('3', "...--");
        addSymbol('4', "....-");
        addSymbol('5', ".....");
        addSymbol('6', "-....");
        addSymbol('7', "--...");
        addSymbol('8', "---..");
        addSymbol('9', "----.");
        addSymbol('.', ".-.-.-");
        addSymbol(',', "--..--");
        addSymbol('?', "..--..");
    }

    /**
     *  Inserts a letter and its Morse code string into the encoding map
     *  and calls treeInsert to insert them into the decoding tree.
     */
    private static void addSymbol(char letter, String code)
    {
        codeMap.put(letter,code);
        treeInsert(letter, code);    
    }
    /**
     *  Inserts a letter and its Morse code string into the
     *  decoding tree.  Each dot-dash string corresponds to a path
     *  in the tree from the root to a node: at a "dot" go left, at
     *  a "dash" go right.  The node at the end of the path holds
     *  the symbol for that code string.
     */
    private static void treeInsert(char letter, String code)
    {
        if (decodeTree==null)
        {
            decodeTree = new TreeNode<Character>(null);
        }
        TreeNode<Character> current = decodeTree;
        for (int i = 0; i < code.length(); i++)
        {
            if (code.charAt(i)=='.')
            {
                if (current.getLeft() == null)
                {
                    current.setLeft(new TreeNode<Character>(null));
                }
                current = current.getLeft();
            }
            else if (code.charAt(i)=='-')
            {
                if (current.getRight() == null)
                {
                    current.setRight(new TreeNode<Character>(null));
                }
                current = current.getRight();
            }
            if (i==code.length()-1)
            {
                current.setValue(letter);
            }
        }
    }

    /**
     *   Converts text into a Morse code message.  Adds a space after
     *   a dot-dash sequence for each letter.  Other spaces in the text
     *   are transferred directly into the encoded message.
     *
     *   @return the encoded message.
     */
    public static String encode(String text)
    {
        StringBuffer morse = new StringBuffer(400);

        for (int i = 0; i<  text.length(); i++)
        {
            char c = text.charAt(i);
            String code = codeMap.get(Character.toUpperCase(c));
            if (c == ' ')
            {
                morse.append(" ");
            }
            else if (code!=null)
            {
                morse.append(code + " ");
            }
        }
 
        return morse.toString();
    }

    /**
     *   Converts a Morse code message into a text string.  Assumes
     *   that dot-dash sequences for each letter are separated by one
     *   space.  Additional spaces are transferred directly into text.
     *
     *   @return the plain text message.
     */
    public static String decode(String morse)
    {
        StringBuffer text = new StringBuffer(100);

        String[] letter = morse.split(" ");

        for (int i=0; i<letter.length;i++)
        {
            if (letter.equals(""))
            {
                text.append(" ");
            }
            else
            {
                TreeNode<Character> current = decodeTree;
                for (int j = 0; j<letter[i].length();j++)
                {
                    if (letter[i].charAt(j)=='.')
                    {
                        current=current.getLeft();
                    }
                    else if (letter[i].charAt(j)=='-')
                    {
                        current = current.getRight();
                    }
                }
                text.append(current.getValue());
            }
        }

        return text.toString();
    }

    //--------------------------------------------------------------------
    // For test purposes only. Not to be used in completing the assignment

    public TreeMap<Character, String> getCodeMap()
    {
        return codeMap;
    }

    public TreeNode<Character> getDecodeTree()
    {
        return decodeTree;
    }
}
