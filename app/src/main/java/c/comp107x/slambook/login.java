package c.comp107x.slambook;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    DatabaseHelper mydb1;
    EditText username,password;
    Button Login;
    Menu myMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mydb1=new DatabaseHelper(this);

        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        Login=(Button) findViewById(R.id.btnlogon);
        showMessage("Note..!","To View Details please Enter valid Username and Password..");
        btnLogin();
    }

    private void btnLogin() {
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (username.getText().toString().trim().equals("")) {
                     username.setError("Please Enter Your Username..!");
                     username.requestFocus();
                 }
                 else if (password.getText().toString().trim().equals("")){
                     password.setError("Please Enter your Password..!");
                     password.requestFocus();
                 }
                 else {
                     if (username.getText().toString().equals("Kranthi") && password.getText().toString().equals("Kumar")){
                         myMenu.findItem(R.id.view).setEnabled(true);
                         showMessage("LogIn Sucess..","You can view Details at top right corner");
                         Toast.makeText(getApplicationContext(),"Login Sucessfull..",Toast.LENGTH_SHORT).show(); 
                     }
                     else {
                         showMessage("Login Failed","Please try with valid username and password..");
                         Toast.makeText(getApplicationContext(),"wrong username or password entered..",Toast.LENGTH_SHORT).show();
                     }
                 }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        myMenu=menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.view:
                Cursor res1=mydb1.getAllData();
                if (res1.getCount()==0) {
                    showMessage("Error", "NothingFound");
                    return true;
                }

                StringBuffer buffer = new StringBuffer();
                while (res1.moveToNext()) {
                    buffer.append("Id :" + res1.getString(0) + "\n");
                    buffer.append("Name :" + res1.getString(1) + "\n");
                    buffer.append("NickName :" + res1.getString(2) + "\n");
                    buffer.append("DateOfBirth :" + res1.getString(3) + "\n");
                    buffer.append("Email :"+res1.getString(4)+"\n");
                    buffer.append("MobileNum :"+res1.getString(5)+"\n");
                    buffer.append("Achivement :" + res1.getString(6) + "\n");
                    buffer.append("Place :" + res1.getString(7) + "\n");
                    buffer.append("Food :"+res1.getString(8)+"\n");
                    buffer.append("Address :"+res1.getString(9)+"\n\n");
                   // buffer.append("Hobbies :"+res1.getString(10)+"\n");
                   // buffer.append("Address :"+res1.getString(11)+"\n\n");

                }
                showMessage("SlamBook Details..", buffer.toString());
                break;

        }
        return true;
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
