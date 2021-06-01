import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.PropertyConfigurator;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

public class App {
	FirestoreOptions firestoreOptions;
	Firestore db;

	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\log4j.properties");
		
		App app = new App();
		app.connect();
		app.register("insoo", "1234", "인수");
		app.login("insoo", "1234");
		
		// 파이어베이스 연동
				//FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
				//	        .setProjectId("intrepid-flight-314510")
				//	        .setCredentials(GoogleCredentials.getApplicationDefault())
				//	        .build();
				//Firestore db = firestoreOptions.getService();
					
				
				// 여기부터는 파이어베이스 연동 테스트용 코드
				//DocumentReference docRef = db.collection("users").document("홍길동");
				// Add document data  with id "alovelace" using a hashmap
				//Map<String, Object> data = new HashMap<>();
				//data.put("생일", "0525");
				//data.put("나이", "20");
				//data.put("성별", "남");
				//asynchronously write data
				//ApiFuture<WriteResult> result = docRef.set(data);
				// ...
				// result.get() blocks on response
				//System.out.println("Update time : " + result.get().getUpdateTime());
					
				
				//DocumentReference docRef2 = db.collection("users").document("aturing");
				// Add document data with an additional field ("middle")
				//Map<String, Object> data2 = new HashMap<>();
				//data.put("first", "Alan");
				//data.put("middle", "Mathison");
				//data.put("last", "Turing");
				//data.put("born", 1912);
				
				//ApiFuture<WriteResult> result2 = docRef2.set(data);
				//System.out.println("Update time : " + result.get().getUpdateTime());
					
					
				// asynchronously retrieve all users
				//ApiFuture<QuerySnapshot> query = db.collection("users").get();
				// ...
				// query.get() blocks on response
				//QuerySnapshot querySnapshot = query.get();
				//List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
				//for (QueryDocumentSnapshot document : documents) {
				//	System.out.println("User: " + document.getId());
				//	System.out.println("생일: " + document.getString("생일"));
				//	if (document.contains("middle")) {
				//	    System.out.println("Middle: " + document.getString("middle"));
				//	}
				//	System.out.println("나이: " + document.getString("나이"));
				//	System.out.println("성별: " + document.getString("성별")); // 숫자형일 경우 getLong 사용
				//}
	}
	
	public void connect() throws IOException {
		// 파이어베이스 연동
		firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
			        .setProjectId("intrepid-flight-314510")
			        .setCredentials(GoogleCredentials.getApplicationDefault())
			        .build();
		db = firestoreOptions.getService();
	}
	
	public void register(String uID, String password, String name) throws InterruptedException, ExecutionException {
		Map<String, Object> data = new HashMap<>();
		DocumentReference docRef = db.collection("users").document(uID);
		
		data.put("uID", uID);
		data.put("password", password);
		data.put("name", name);
		
		ApiFuture<WriteResult> result = docRef.set(data);
		System.out.println("Update time : " + result.get().getUpdateTime());
	}
	
	public boolean login(String uID, String password) throws InterruptedException, ExecutionException {
		Map<String, Object> data = new HashMap<>();

		DocumentReference docRef = db.collection("users").document(uID);
		ApiFuture<DocumentSnapshot> result = docRef.get();
		DocumentSnapshot document = result.get();
		
		if(document.exists()) {
			User user = document.toObject(User.class);
			
			if(user.getUID().equals(uID) && user.getPassword().equals(password)) {
				System.out.println(user.getName() + "님 로그인");
				return true;
			} else if(user.getUID().equals(uID) || user.getPassword().equals(password)) {
				System.out.println("아이디 또는 비밀번호 오류입니다.");
			} else {
				System.out.println("로그인 실패");
			}
		} else {
			System.out.println("로그인 실패");
		}

		return false;
	}
	
	public void selectUserCollection(String uID, String password) throws InterruptedException, ExecutionException {
		// asynchronously retrieve all users
		ApiFuture<QuerySnapshot> query = db.collection("users").get();
		// ...
		// query.get() blocks on response
		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documents) {
			System.out.println("User: " + document.getId());
			System.out.println("생일: " + document.getString("생일"));
			if (document.contains("middle")) {
			    System.out.println("Middle: " + document.getString("middle"));
			}
			System.out.println("나이: " + document.getString("나이"));
			System.out.println("성별: " + document.getString("성별")); // 숫자형일 경우 getLong 사용
		}

	}
	
	public void recordTime() {
		//DocumentReference docRed
	}
}