package com.softcodeinfotech.helpapp.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.softcodeinfotech.helpapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import static com.facebook.appevents.UserDataStore.EMAIL;

public class SignupLoginActivity extends AppCompatActivity {
    Button Signup, Login;
    TextView skip;


    //gmail facebook Integration

    Button gmail;
    LoginButton facebook;
    private static final int RC_SIGN_IN = 101;
    // CallbackManager callbackManager;

    private String TAG = "SignupActivity";

    //Google SignIn
    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.signuplogintest);

        setUpWidget();

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupIntent = new Intent(SignupLoginActivity.this, SignupActivity.class);
                startActivity(signupIntent);
               // finish();

            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent loginIntent = new Intent(SignupLoginActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();

            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mainIntent = new Intent(SignupLoginActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();

            }
        });


        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();

        //gmail

        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
                Toast.makeText(SignupLoginActivity.this, "Gmail", Toast.LENGTH_SHORT).show();
            }
        });

        //facebook
        callbackManager = CallbackManager.Factory.create();
        facebook.setReadPermissions(Arrays.asList(EMAIL));
        facebook.setReadPermissions("email", "public_profile");

        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        facebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String userId = loginResult.getAccessToken().getUserId();
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                displayUserInfo(object);
                            }
                        });

                Bundle bundle = new Bundle();
                bundle.putString("fields", "first_name,last_name,email,id");
                graphRequest.setParameters(bundle);
                graphRequest.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }


    private void displayUserInfo(JSONObject object) {
        String name="";
        String first_name = "";
        String last_name = "", email = "", uid = "";

        try {
            first_name = object.getString("first_name");
            last_name = object.getString("last_name");
            email = object.getString("email");
            uid = object.getString("id");

            name =first_name+" "+last_name;

        } catch (JSONException e) {
            e.printStackTrace();
        }

       /* Toast.makeText(SignupLoginActivity.this, "" + first_name + "" + last_name, Toast.LENGTH_SHORT).show();
        Toast.makeText(SignupLoginActivity.this, "" + email, Toast.LENGTH_SHORT).show();
*/
        Intent intent = new Intent(SignupLoginActivity.this,SignupActivity.class);
        intent.putExtra("username",name);
        intent.putExtra("password",uid);
        startActivity(intent);
       // finishAffinity();


    }


    //gmail

    //firebase signin
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            // Toast.makeText(SigninActivity.this, "sign in sucess", Toast.LENGTH_SHORT).show();
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(SignupLoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    public void updateUI() {

        if (mAuth.getCurrentUser() != null) {
            FirebaseUser user = mAuth.getCurrentUser();
            String name, email, uid, phone_number, address;
            name = user.getDisplayName();
            email = user.getEmail();
            uid = user.getUid();
            // address=user.getPhotoUrl().toString();
            phone_number = user.getPhoneNumber();


            Uri photoUrl = user.getPhotoUrl();
            String abc = photoUrl.toString();

            // type = "Gmail";

          /*  Toast.makeText(this, "name=" + name, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "email=" + email, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "phone=" + phone_number, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "" + photoUrl.toString(), Toast.LENGTH_SHORT).show();
            Log.v("uri", abc);*/

            Intent intent = new Intent(SignupLoginActivity.this,SignupActivity.class);
            intent.putExtra("username",name);
            intent.putExtra("password",uid);
            startActivity(intent);
           // finishAffinity();


        }
    }



    private void setUpWidget() {
        Signup = findViewById(R.id.button);
        Login = findViewById(R.id.button2);
        skip = findViewById(R.id.textView15);
        gmail= findViewById(R.id.button8);
        facebook = findViewById(R.id.login_button);

    }
}
