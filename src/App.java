import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.PropertyConfigurator;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

public class App {
  
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\log4j.properties");

		FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
			        .setProjectId("intrepid-flight-314510")
			        .setCredentials(GoogleCredentials.getApplicationDefault())
			        .build();
		Firestore db = firestoreOptions.getService();
			
		
		// 여기부터는 파이어베이스 연동 테스트용 코드
		DocumentReference docRef = db.collection("users").document("alovelace");
		// Add document data  with id "alovelace" using a hashmap
		Map<String, Object> data = new HashMap<>();
		data.put("first", "Ada");
		data.put("last", "Lovelace");
		data.put("born", 1815);
		//asynchronously write data
		ApiFuture<WriteResult> result = docRef.set(data);
		// ...
		// result.get() blocks on response
		System.out.println("Update time : " + result.get().getUpdateTime());
			
		
		DocumentReference docRef2 = db.collection("users").document("aturing");
		// Add document data with an additional field ("middle")
		Map<String, Object> data2 = new HashMap<>();
		data.put("first", "Alan");
		data.put("middle", "Mathison");
		data.put("last", "Turing");
		data.put("born", 1912);
		
		ApiFuture<WriteResult> result2 = docRef.set(data);
		System.out.println("Update time : " + result.get().getUpdateTime());
			
			
		// asynchronously retrieve all users
		ApiFuture<QuerySnapshot> query = db.collection("users").get();
		// ...
		// query.get() blocks on response
		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documents) {
			System.out.println("User: " + document.getId());
			System.out.println("First: " + document.getString("first"));
			if (document.contains("middle")) {
			    System.out.println("Middle: " + document.getString("middle"));
			}
			System.out.println("Last: " + document.getString("last"));
			System.out.println("Born: " + document.getLong("born"));
		}
	}
}