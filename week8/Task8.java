import java.util.*;

class Solution {
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();

        for (String email : emails) {
            String[] parts = email.split("@");
            String local = parts[0];
            String domain = parts[1];

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < local.length(); i++) {
                char c = local.charAt(i);

                if (c == '+') break;
                if (c != '.') sb.append(c);
            }

            String normalized = sb.toString() + "@" + domain;
            set.add(normalized);
        }

        return set.size();
    }
}
