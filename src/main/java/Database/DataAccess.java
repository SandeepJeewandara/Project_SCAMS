    package Database;

    import com.example.scams_ood.*;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.List;

    public class DataAccess {

        //Static lists uses for all operations
        private static List<Club> clubs;
        private static List<ClubAdvisor> clubAdvisors;
        private static List<Event> events;
        private static List<Student> students;






        static {
            clubs = new ArrayList<>();
            clubAdvisors = new ArrayList<>();
            students=new ArrayList<>();
            events=new ArrayList<>();


            try {
                //Set Database Connection
                Connection connection = DatabaseConnectionTest.getConnection();

                //Fetch Tables
                fetchClubAdvisors(connection);
                fetchClubs(connection);
                fetchStudents(connection);
                fetchEvents(connection);

                // Fetch club memberships
                fetchClubMemberships(connection);
                fetchEventParticipation(connection);


                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }




        //Update Club Advisors list from Database
        private static void fetchClubAdvisors(Connection connection) throws SQLException {
            String query = "SELECT * FROM Club_Advisor";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    ClubAdvisor clubAdvisor = new ClubAdvisor(
                            resultSet.getString("AdvisorID"),
                            resultSet.getString("First_name"),
                            resultSet.getString("Gender"),
                            resultSet.getString("Email"),
                            resultSet.getDate("DOB"),
                            resultSet.getString("Username"),
                            resultSet.getString("Password")
                    );
                    clubAdvisors.add(clubAdvisor);
                }
            }
        }

        //Update Club list from Database
        private static void fetchClubs(Connection connection) throws SQLException {
            String query = "SELECT * FROM Club";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Club club = new Club(
                            resultSet.getString("ClubID"),
                            resultSet.getString("Club_name"),
                            resultSet.getString("Club_type"),
                            resultSet.getDate("Started_date").toLocalDate(),
                            resultSet.getString("Club_description"),
                            resultSet.getString("Club_logo_path"),
                            null
                    );
                    clubs.add(club);

                    // Connect Club and ClubAdvisor
                    String advisorId = resultSet.getString("AdvisorID");
                    connectClubAndAdvisor(club, advisorId);
                }
            }
        }

        //Update Student list from Database
        private static void fetchStudents(Connection connection) throws SQLException {
            String query = "SELECT * FROM Student";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Student student = new Student(
                            resultSet.getString("StudentID"),
                            resultSet.getString("First_name"),
                            resultSet.getString("Gender"),
                            resultSet.getString("Email"),
                            resultSet.getDate("DOB"),
                            resultSet.getString("User_name"),
                            resultSet.getString("Password")
                    );
                    students.add(student);

                }
            }
        }

        //Update Events list from Database
        private static void fetchEvents(Connection connection) throws SQLException {
            String query = "SELECT * FROM Event";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Event event = new Event();
                    event.setEventId(resultSet.getString("EventID"));
                    event.setEventName(resultSet.getString("Event_name"));
                    event.setEventDate(resultSet.getDate("Event_date"));
                    event.setEventTime(resultSet.getTime("Event_time"));
                    event.setEventDescription(resultSet.getString("Event_description"));

                    // Connect Event, Club, and Students
                    connectEventAndClub(event, resultSet.getString("ClubID"));

                    events.add(event);

                    // Associate the event with the corresponding club
                    for (Club club : clubs) {
                        if (club.getClubId().equals(resultSet.getString("ClubID"))) {
                            club.addEvent(event);
                            break;
                        }
                    }

                }
            }
        }


        //Update ClubMembership association from Database
        private static void fetchClubMemberships(Connection connection) throws SQLException {
            String query = "SELECT * FROM Student_Club";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String studentId = resultSet.getString("StudentID");
                    String clubId = resultSet.getString("ClubID");

                    // Find the student and club based on their IDs
                    Student student = findStudentById(studentId);
                    Club club = findClubById(clubId);

                    // Update the lists with associations
                    if (student != null && club != null) {
                        student.getClubsJoined().add(club);
                        club.getMembers().add(student);
                    }
                }
            }
        }

        private static Student findStudentById(String studentId) {
            for (Student student : students) {
                if (student.getStudentId().equals(studentId)) {
                    return student;
                }
            }
            return null;
        }

        private static Club findClubById(String clubId) {
            for (Club club : clubs) {
                if (club.getClubId().equals(clubId)) {
                    return club;
                }
            }
            return null;
        }

        //Update EventParticipation association from Database
        private static void fetchEventParticipation(Connection connection) throws SQLException {
            String query = "SELECT * FROM Event_Registration";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String studentId = resultSet.getString("StudentID");
                    String eventId = resultSet.getString("EventID");
                    boolean attendance = resultSet.getBoolean("Attendance");

                    // Find the student and Event based on their IDs
                    Student student = findStudentById(studentId);
                    Event event = findEventById(eventId);

                    // Update the lists with associations
                    if (student != null && event != null) {
                        student.getEventsjoined().add(event);
                        event.getMembers().add(student);

                    }
                }
            }
        }

        public static boolean isStudentMemberOfClub(String studentId, String clubId) {
            try (Connection connection = DatabaseConnectionTest.getConnection()) {
                String query = "SELECT COUNT(*) FROM Student_Club WHERE StudentID = ? AND ClubID = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, studentId);
                    preparedStatement.setString(2, clubId);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            return resultSet.getInt(1) > 0;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return false;
        }
        private static Event findEventById(String eventId) {
            for (Event event : events) {
                if (event.getEventId().equals(eventId)) {
                    return event;
                }
            }
            return null;
        }


        private static void connectEventAndClub(Event event, String clubId) {
            for (Club club : clubs) {
                if (club.getClubId().equals(clubId)) {
                    event.setClubID(club);
                    break;
                }
            }
        }


        private static void connectClubAndAdvisor(Club club, String advisorId) {
            for (ClubAdvisor advisor : clubAdvisors) {
                if (advisor.getAdvisorId().equals(advisorId)) {
                    club.setClubAdvisor(advisor);
                    break;
                }
            }
        }


        public static List<Club> getClubs() {
            return clubs;
        }

        public static List<ClubAdvisor> getClubAdvisors() {
            return clubAdvisors;
        }

        public static List<Student> getStudents(){ return students;}

        public static List<Event> getEvents(){ return  events;}

        public static void addClub(Club newClub) {
            clubs.add(newClub);
        }

        public static void removeClub(Club clubToRemove) {
            clubs.remove(clubToRemove);
        }

        public static void addEvent(Event newEvent) {
            events.add(newEvent);
        }

        public static void removeEvent(Event eventToRemove) {
            events.remove(eventToRemove);
        }

        }
