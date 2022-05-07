import java.util.*;
import java.util.stream.Collectors;

public class Spotify {

    static LinkedList<String> Personlist = new LinkedList<>();
    static Map<String, LinkedList<String>> listLikedSongsForUser = new HashMap<>();

    public static void main(String[] args) {

        System.out.println("Welcome to Spotify");
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Enter command:");
            String line = sc.nextLine();
            if(line == null) break;
            run(line);
        }

    }

    private static void run(String source){

        StringTokenizer st = new StringTokenizer(source);
        while(st.hasMoreTokens()) {
            switch (st.nextToken()) {
                case "C" -> {
                    if(st.countTokens()>1){
                        System.out.println("Only one name is required");
                    } else if(st.countTokens()==1) {
                        String PersonName = st.nextToken();
                        if (Personlist.contains(PersonName)) {
                            System.out.println("Name exists");
                        }else {
                            Personlist.add(PersonName);
                            System.out.println("Name stored");
                            System.out.println("No. of people:" + Personlist.size());
                        }
                    } else {
                        System.out.println("Unable to perform C command");
                    }
                }

                case "S" -> {
                    if(st.countTokens()>1) {
                        String person = st.nextToken();
                        String correct;
                        if (!Personlist.contains(person)) {
                            Personlist.add(person);
                            System.out.println("Username didn't exist but has been added");
                            System.out.println("No. of people:" + Personlist.size());
                        } else {
                            correct = person;
                            listLikedSongsForUser.computeIfAbsent(correct, k -> new LinkedList<>());
                            String liked = st.nextToken();
                            if (!listLikedSongsForUser.get(correct).contains(liked)) {
                                listLikedSongsForUser.get(correct).add(liked);
                                System.out.println("This song " + liked + " has been liked by " + correct);
                                System.out.println(listLikedSongsForUser.get(correct));
                            } else {
                                System.out.println("The song already exists");
                            }
                        }
                    } else {
                        System.out.println("Unable to perform S command");
                    }
                }

                case "E" -> {
                    if(st.countTokens()>1) {
                        String person = st.nextToken();
                        String correct;
                        if (!Personlist.contains(person)) {
                            System.out.println("Username doesn't exists");
                        } else {
                            correct = person;
                            listLikedSongsForUser.computeIfAbsent(correct, k -> new LinkedList<>());
                            String delete = st.nextToken();
                            if (listLikedSongsForUser.get(correct).contains(delete)) {
                                listLikedSongsForUser.get(correct).remove(delete);
                                System.out.println("This song " + delete + ",that " + correct+" had liked, has been deleted.");
                                System.out.println(listLikedSongsForUser.get(correct));
                            } else {
                                System.out.println("The song doesnt exists");
                            }
                        }
                    } else {
                        System.out.println("Unable to perform E command");
                    }
                }

                case "L" -> {
                    if (st.countTokens()>1){
                        System.out.println("Unable to perform L command");
                    } else if (st.countTokens()==1){
                        String PersonName = st.nextToken();
                        String correct;
                        if (!Personlist.contains(PersonName))  {
                            System.out.println("Username doesn't exist");
                        } else {
                            correct = PersonName;
                            listLikedSongsForUser.computeIfAbsent(correct, k -> new LinkedList<>());
                            System.out.println(correct+": "+listLikedSongsForUser.get(correct));
                        }
                    } else{
                        System.out.println("Unable to perform L command");
                    }
                }

                case "N" -> {
                    if(st.countTokens()<1){
                        System.out.println(Personlist);
                    }else {
                        System.out.println("Unable to perform N command");
                    }
                }

                case "M" -> {
                    if (st.countTokens()<1) {
                        LinkedList<String> SongList;
                        ArrayList<String> Song = new ArrayList<>();
                        for (String s : Personlist) {
                            SongList = listLikedSongsForUser.get(s);
                            Song.addAll(SongList);
                        }
                        Set<String> set = new HashSet<>(Song);
                        Song.clear();
                        Song.addAll(set);
                        System.out.println(Song);
                    }else {
                        System.out.println("Unable to perform M command");
                    }
                }

                case "R" -> {
                    if (st.countTokens()<1) {
                        LinkedList<String> SongList;
                        ArrayList<String> Song = new ArrayList<>();
                        for (String s : Personlist) {
                            SongList = listLikedSongsForUser.get(s);
                            Song.addAll(SongList);
                        }
                        Map<String, Long> map = Song.stream()
                                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
                        List<Map.Entry<String, Long>> result = map.entrySet().stream()
                                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                .limit(3)
                                .collect(Collectors.toList());
                        System.out.println(result);
                    }else {
                        System.out.println("Unable to perform R command");
                    }
                }
                default -> System.out.println("Wrong input command");
            }
        }
    }
}
