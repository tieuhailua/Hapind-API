package aptech.dating.DTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import aptech.dating.model.Banned;
import aptech.dating.model.Block;
import aptech.dating.model.Drinking;
import aptech.dating.model.Family;
import aptech.dating.model.Habit;
import aptech.dating.model.Literacy;
import aptech.dating.model.Matching;
import aptech.dating.model.Message;
import aptech.dating.model.Purpose;
import aptech.dating.model.Report;
import aptech.dating.model.Smoking;
import aptech.dating.model.Status;
import aptech.dating.model.UserConversation;
import aptech.dating.model.UserExercise;
import aptech.dating.model.UserExpecting;
import aptech.dating.model.UserHobby;
import aptech.dating.model.UserImage;
import aptech.dating.model.UserLanguage;
import aptech.dating.model.UserMusic;
import aptech.dating.model.UserPet;
import aptech.dating.model.UserSinger;
import aptech.dating.model.VideoCall;
import aptech.dating.model.Work;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDTO {
	private Integer id;
	@NotNull(message = "Drinking must be selected")
	private Drinking drinking;
	
	@NotNull(message = "Family must be selected")
	private Family family;
	
	@NotNull(message = "Habit must be selected")
	private Habit habit;
	
	@NotNull(message = "Literacy must be selected")
	private Literacy literacy;
	
	@NotNull(message = "Purpose must be selected")
	private Purpose purpose;
	
	@NotNull(message = "Smoking must be selected")
	private Smoking smoking;
	
	@NotNull(message = "Status must be selected")
	private Status status;
	
	@NotNull(message = "Work must be selected")
	private Work work;
	
	@NotEmpty(message="Email can't be blank")
	private String email;
	
	@NotEmpty(message="Phone can't be blank")
	private String phone;
	
	@NotEmpty(message="Password can't be blank")
	@Size(min = 8, max = 32, message = "Password must be between 8 and 32 characters")
	private String password;
	
	@NotEmpty(message="Full Name can't be blank")
	private String fullname;
	
	@NotNull(message = "DOB must be selected")
	private Date dob;
	
	@NotEmpty(message="Gender can't be blank")
	private String gender;
	
	@NotEmpty(message="Finding can't be blank")
	private String finding;
	
	@NotNull(message = "Distance must be selected")
	private int distance;
	
	@NotEmpty(message="Address can't be blank")
	private String address;
	
	@NotEmpty(message="School can't be blank")
	private String school;
	
	@NotEmpty(message="Description can't be blank")
	private String description;
	
	@NotNull(message = "Height must be selected")
	private Integer height;
	
	@NotNull(message = "Weight must be selected")
	private Integer weight;
	
	@NotEmpty(message="Zodiac can't be blank")
	private String zodiac;
	
	@NotNull(message = "Create Time must be selected")
	private Date createTime;
	
	@NotNull(message = "Last Login must be selected")
	private Date lastLogin;
	
	private Boolean online = false;
	
	@NotNull(message = "Matching Second must be selected")
	private Set<Matching> matchingsForSecondUserId = new HashSet<Matching>(0);
	
	@NotNull(message = "Mathching Report must be selected")
	private Set<Report> reportsForReportedId = new HashSet<Report>(0);
	
	@NotNull(message = "User Conversation must be selected")
	private Set<UserConversation> userConversations = new HashSet<UserConversation>(0);
	
	@NotNull(message = "User Exercise must be selected")
	private Set<UserExercise> userExercises = new HashSet<UserExercise>(0);
	
	@NotNull(message = "Video Call must be selected")
	private Set<VideoCall> videoCallsForReceiverId = new HashSet<VideoCall>(0);
	
	@NotNull(message = "Message must be selected")
	private Set<Message> messages = new HashSet<Message>(0);
	
	@NotNull(message = "User Hobby must be selected")
	private Set<UserHobby> userHobbies = new HashSet<UserHobby>(0);
	
	@NotNull(message = "Block for Blocked must be selected")
	private Set<Block> blocksForBlockedId = new HashSet<Block>(0);
	
	@NotNull(message = "User Image must be selected")
	private Set<UserImage> userImages = new HashSet<UserImage>(0);
	
	@NotNull(message = "Matching For First must be selected")
	private Set<Matching> matchingsForFirstUserId = new HashSet<Matching>(0);
	
	@NotNull(message = "User Language must be selected")
	private Set<UserLanguage> userLanguages = new HashSet<UserLanguage>(0);
	
	@NotNull(message = "Video Call For Caller must be selected")
	private Set<VideoCall> videoCallsForCallerId = new HashSet<VideoCall>(0);
	
	@NotNull(message = "Block For Use must be selected")
	private Set<Block> blocksForUseId = new HashSet<Block>(0);
	
	@NotNull(message = "User Expecting must be selected")
	private Set<UserExpecting> userExpectings = new HashSet<UserExpecting>(0);
	
	@NotNull(message = "Banned must be selected")
	private Set<Banned> banneds = new HashSet<Banned>(0);
	
	@NotNull(message = "User Pet must be selected")
	private Set<UserPet> userPets = new HashSet<UserPet>(0);
	
	@NotNull(message = "User Singer must be selected")
	private Set<UserSinger> userSingers = new HashSet<UserSinger>(0);
	
	@NotNull(message = "Report For Reporter must be selected")
	private Set<Report> reportsForReporterId = new HashSet<Report>(0);
	
	@NotNull(message = "User Music must be selected")
	private Set<UserMusic> userMusics = new HashSet<UserMusic>(0);

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDTO(@NotNull(message = "Drinking must be selected") Drinking drinking,
			@NotNull(message = "Family must be selected") Family family,
			@NotNull(message = "Habit must be selected") Habit habit,
			@NotNull(message = "Literacy must be selected") Literacy literacy,
			@NotNull(message = "Purpose must be selected") Purpose purpose,
			@NotNull(message = "Smoking must be selected") Smoking smoking,
			@NotNull(message = "Status must be selected") Status status,
			@NotNull(message = "Work must be selected") Work work,
			@NotEmpty(message = "Email can't be blank") String email,
			@NotEmpty(message = "Phone can't be blank") String phone,
			@NotEmpty(message = "Password can't be blank") @Size(min = 8, max = 32, message = "Password must be between 8 and 32 characters") String password,
			@NotEmpty(message = "Full Name can't be blank") String fullname,
			@NotNull(message = "DOB must be selected") Date dob,
			@NotEmpty(message = "Gender can't be blank") String gender,
			@NotEmpty(message = "Finding can't be blank") String finding,
			@NotNull(message = "Distance must be selected") int distance,
			@NotEmpty(message = "Address can't be blank") String address,
			@NotEmpty(message = "School can't be blank") String school,
			@NotEmpty(message = "Description can't be blank") String description,
			@NotNull(message = "Height must be selected") Integer height,
			@NotNull(message = "Weight must be selected") Integer weight,
			@NotEmpty(message = "Zodiac can't be blank") String zodiac,
			@NotNull(message = "Create Time must be selected") Date createTime,
			@NotNull(message = "Last Login must be selected") Date lastLogin, Boolean online,
			@NotNull(message = "Matching Second must be selected") Set<Matching> matchingsForSecondUserId,
			@NotNull(message = "Mathching Report must be selected") Set<Report> reportsForReportedId,
			@NotNull(message = "User Conversation must be selected") Set<UserConversation> userConversations,
			@NotNull(message = "User Exercise must be selected") Set<UserExercise> userExercises,
			@NotNull(message = "Video Call must be selected") Set<VideoCall> videoCallsForReceiverId,
			@NotNull(message = "Message must be selected") Set<Message> messages,
			@NotNull(message = "User Hobby must be selected") Set<UserHobby> userHobbies,
			@NotNull(message = "Block for Blocked must be selected") Set<Block> blocksForBlockedId,
			@NotNull(message = "User Image must be selected") Set<UserImage> userImages,
			@NotNull(message = "Matching For First must be selected") Set<Matching> matchingsForFirstUserId,
			@NotNull(message = "User Language must be selected") Set<UserLanguage> userLanguages,
			@NotNull(message = "Video Call For Caller must be selected") Set<VideoCall> videoCallsForCallerId,
			@NotNull(message = "Block For Use must be selected") Set<Block> blocksForUseId,
			@NotNull(message = "User Expecting must be selected") Set<UserExpecting> userExpectings,
			@NotNull(message = "Banned must be selected") Set<Banned> banneds,
			@NotNull(message = "User Pet must be selected") Set<UserPet> userPets,
			@NotNull(message = "User Singer must be selected") Set<UserSinger> userSingers,
			@NotNull(message = "Report For Reporter must be selected") Set<Report> reportsForReporterId,
			@NotNull(message = "User Music must be selected") Set<UserMusic> userMusics) {
		super();
		this.drinking = drinking;
		this.family = family;
		this.habit = habit;
		this.literacy = literacy;
		this.purpose = purpose;
		this.smoking = smoking;
		this.status = status;
		this.work = work;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.fullname = fullname;
		this.dob = dob;
		this.gender = gender;
		this.finding = finding;
		this.distance = distance;
		this.address = address;
		this.school = school;
		this.description = description;
		this.height = height;
		this.weight = weight;
		this.zodiac = zodiac;
		this.createTime = createTime;
		this.lastLogin = lastLogin;
		this.online = online;
		this.matchingsForSecondUserId = matchingsForSecondUserId;
		this.reportsForReportedId = reportsForReportedId;
		this.userConversations = userConversations;
		this.userExercises = userExercises;
		this.videoCallsForReceiverId = videoCallsForReceiverId;
		this.messages = messages;
		this.userHobbies = userHobbies;
		this.blocksForBlockedId = blocksForBlockedId;
		this.userImages = userImages;
		this.matchingsForFirstUserId = matchingsForFirstUserId;
		this.userLanguages = userLanguages;
		this.videoCallsForCallerId = videoCallsForCallerId;
		this.blocksForUseId = blocksForUseId;
		this.userExpectings = userExpectings;
		this.banneds = banneds;
		this.userPets = userPets;
		this.userSingers = userSingers;
		this.reportsForReporterId = reportsForReporterId;
		this.userMusics = userMusics;
	}

	public Drinking getDrinking() {
		return drinking;
	}

	public void setDrinking(Drinking drinking) {
		this.drinking = drinking;
	}

	public Family getFamily() {
		return family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}

	public Habit getHabit() {
		return habit;
	}

	public void setHabit(Habit habit) {
		this.habit = habit;
	}

	public Literacy getLiteracy() {
		return literacy;
	}

	public void setLiteracy(Literacy literacy) {
		this.literacy = literacy;
	}

	public Purpose getPurpose() {
		return purpose;
	}

	public void setPurpose(Purpose purpose) {
		this.purpose = purpose;
	}

	public Smoking getSmoking() {
		return smoking;
	}

	public void setSmoking(Smoking smoking) {
		this.smoking = smoking;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFinding() {
		return finding;
	}

	public void setFinding(String finding) {
		this.finding = finding;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getZodiac() {
		return zodiac;
	}

	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public Set<Matching> getMatchingsForSecondUserId() {
		return matchingsForSecondUserId;
	}

	public void setMatchingsForSecondUserId(Set<Matching> matchingsForSecondUserId) {
		this.matchingsForSecondUserId = matchingsForSecondUserId;
	}

	public Set<Report> getReportsForReportedId() {
		return reportsForReportedId;
	}

	public void setReportsForReportedId(Set<Report> reportsForReportedId) {
		this.reportsForReportedId = reportsForReportedId;
	}

	public Set<UserConversation> getUserConversations() {
		return userConversations;
	}

	public void setUserConversations(Set<UserConversation> userConversations) {
		this.userConversations = userConversations;
	}

	public Set<UserExercise> getUserExercises() {
		return userExercises;
	}

	public void setUserExercises(Set<UserExercise> userExercises) {
		this.userExercises = userExercises;
	}

	public Set<VideoCall> getVideoCallsForReceiverId() {
		return videoCallsForReceiverId;
	}

	public void setVideoCallsForReceiverId(Set<VideoCall> videoCallsForReceiverId) {
		this.videoCallsForReceiverId = videoCallsForReceiverId;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public Set<UserHobby> getUserHobbies() {
		return userHobbies;
	}

	public void setUserHobbies(Set<UserHobby> userHobbies) {
		this.userHobbies = userHobbies;
	}

	public Set<Block> getBlocksForBlockedId() {
		return blocksForBlockedId;
	}

	public void setBlocksForBlockedId(Set<Block> blocksForBlockedId) {
		this.blocksForBlockedId = blocksForBlockedId;
	}

	public Set<UserImage> getUserImages() {
		return userImages;
	}

	public void setUserImages(Set<UserImage> userImages) {
		this.userImages = userImages;
	}

	public Set<Matching> getMatchingsForFirstUserId() {
		return matchingsForFirstUserId;
	}

	public void setMatchingsForFirstUserId(Set<Matching> matchingsForFirstUserId) {
		this.matchingsForFirstUserId = matchingsForFirstUserId;
	}

	public Set<UserLanguage> getUserLanguages() {
		return userLanguages;
	}

	public void setUserLanguages(Set<UserLanguage> userLanguages) {
		this.userLanguages = userLanguages;
	}

	public Set<VideoCall> getVideoCallsForCallerId() {
		return videoCallsForCallerId;
	}

	public void setVideoCallsForCallerId(Set<VideoCall> videoCallsForCallerId) {
		this.videoCallsForCallerId = videoCallsForCallerId;
	}

	public Set<Block> getBlocksForUseId() {
		return blocksForUseId;
	}

	public void setBlocksForUseId(Set<Block> blocksForUseId) {
		this.blocksForUseId = blocksForUseId;
	}

	public Set<UserExpecting> getUserExpectings() {
		return userExpectings;
	}

	public void setUserExpectings(Set<UserExpecting> userExpectings) {
		this.userExpectings = userExpectings;
	}

	public Set<Banned> getBanneds() {
		return banneds;
	}

	public void setBanneds(Set<Banned> banneds) {
		this.banneds = banneds;
	}

	public Set<UserPet> getUserPets() {
		return userPets;
	}

	public void setUserPets(Set<UserPet> userPets) {
		this.userPets = userPets;
	}

	public Set<UserSinger> getUserSingers() {
		return userSingers;
	}

	public void setUserSingers(Set<UserSinger> userSingers) {
		this.userSingers = userSingers;
	}

	public Set<Report> getReportsForReporterId() {
		return reportsForReporterId;
	}

	public void setReportsForReporterId(Set<Report> reportsForReporterId) {
		this.reportsForReporterId = reportsForReporterId;
	}

	public Set<UserMusic> getUserMusics() {
		return userMusics;
	}

	public void setUserMusics(Set<UserMusic> userMusics) {
		this.userMusics = userMusics;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
