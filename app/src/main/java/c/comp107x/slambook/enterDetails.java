package c.comp107x.slambook;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class enterDetails extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText editName,editNickname,editDateofbirth,editTextId,editEmail,editMobile;
    EditText editachivement,editplace,editfood,editaddress;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details);
        mydb=new DatabaseHelper(this);

        editName = (EditText)findViewById(R.id.editnameid);
        editNickname = (EditText)findViewById(R.id.editnicknameid);
        editDateofbirth = (EditText)findViewById(R.id.editdateofbirthid);
        editTextId = (EditText)findViewById(R.id.editid);
        editEmail = (EditText) findViewById(R.id.editEmailid);
        editMobile = (EditText) findViewById(R.id.editMobileid);

        editachivement = (EditText) findViewById(R.id.editfirstachivementid);
        editplace = (EditText) findViewById(R.id.editfavoriteplaceid);
        editfood = (EditText) findViewById(R.id.editfavoritefoodid);
        editaddress = (EditText) findViewById(R.id.editaddressid);
      //  edithobbies = (EditText) findViewById(R.id.edithobbiesid);
      //  editaddress = (EditText) findViewById(R.id.editaddressid);

        btnAddData = (Button)findViewById(R.id.button_add);
        AddData();

    }

    public void AddData(){
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (editName.getText().toString().trim().equals("")){
                    editName.setError("Please Enter Your Name..!");
                    editName.requestFocus();
                }
                else if (editNickname.getText().toString().trim().equals("")){
                    editNickname.setError("Please Enter Your NickName..!");
                    editNickname.requestFocus();
                }
                else if (editDateofbirth.getText().toString().trim().equals("")){
                    editDateofbirth.setError("Please Enter Your Marks..!");
                    editDateofbirth.requestFocus();
                }
                else if (editEmail.getText().toString().trim().equals("")){
                    editEmail.setError("Please Enter Your Email Address..!");
                    editEmail.requestFocus();
                }
                else if (editMobile.getText().toString().trim().equals("")){
                    editMobile.setError("Please Enter Your MobileNumber..!");
                    editMobile.requestFocus();
                }
               else if (editachivement.getText().toString().trim().equals("")){
                   editachivement.setError("Please Enter Your Achivement..!");
                   editachivement.requestFocus();
               }
               else if (editfood.getText().toString().trim().equals("")){
                   editfood.setError("Please Enter Your Favotite Food..!");
                   editfood.requestFocus();
               }
               else if (editplace.getText().toString().trim().equals("")){
                   editplace.setError("Please Enter Your Favorite Place..!");
                   editplace.requestFocus();
               }
               else if (editaddress.getText().toString().trim().equals("")){
                   editaddress.setError("Please Enter Your Favorite Colour..!");
                   editaddress.requestFocus();
               }
             /*  else if (edithobbies.getText().toString().trim().equals("")){
                   edithobbies.setError("Please Enter Your Hobbies..!");
                   edithobbies.requestFocus();
               }
               else if (editaddress.getText().toString().trim().equals("")){
                   editaddress.setError("Please Enter Your Address..!");
                   editaddress.requestFocus();
               }*/

                else {
                        boolean isInserted = mydb.insertData(editName.getText().toString(),
                                editNickname.getText().toString(),
                                editDateofbirth.getText().toString(),
                                editEmail.getText().toString(),
                                editMobile.getText().toString(),
                                editachivement.getText().toString(),
                                editplace.getText().toString(),
                                editfood.getText().toString(),
                                editaddress.getText().toString()
                             //   edithobbies.getText().toString(),
                             //   editaddress.getText().toString()
                                );
                        if (isInserted == true){
                            Toast.makeText(enterDetails.this, "DataInserted", Toast.LENGTH_SHORT).show();
                        showMessage("Sucess..!","Data Added Sucessfully");}
                        else{
                            Toast.makeText(enterDetails.this, "DataNot Inserted", Toast.LENGTH_SHORT).show();
                        showMessage("Failure..!","Failure to Add Data..?");}
                        clearAll();
                }
            }
        });
    }


    public void showMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.updateid:
                if (editTextId.getText().toString().trim().equals("")) {
                    editTextId.setError("Please enter the Id along with details Update data");
                    editTextId.requestFocus();
                    showMessage("Note!","You Must Enter Both values and ID:\nID which are present in ViewAll at menu");

                }
                else {
                    boolean isUpdate = mydb.updateData(editTextId.getText().toString(),
                            editName.getText().toString(),
                            editNickname.getText().toString(),
                            editDateofbirth.getText().toString(),
                            editEmail.getText().toString(),
                            editMobile.getText().toString(),
                            editachivement.getText().toString(),
                            editplace.getText().toString(),
                            editfood.getText().toString(),
                            editaddress.getText().toString()
                           // edithobbies.getText().toString(),
                           // editaddress.getText().toString()
                            );
                    if (isUpdate == true){
                        Toast.makeText(enterDetails.this, "DataUpdate", Toast.LENGTH_SHORT).show();
                    showMessage("Sucess..","Data was updated sucessfully");}
                    else{
                        Toast.makeText(enterDetails.this, "DataNot Updated", Toast.LENGTH_SHORT).show();
                    showMessage("Failure..!","Failure to update your data\n\nPlease Enter the Valid credentials..?");}
                    clearAll();
                }
                break;

            case R.id.Deleteid:
                if (editTextId.getText().toString().trim().equals("")) {
                    editTextId.setError("Please enter the Id to delete");
                    editTextId.requestFocus();
                    showMessage("Note!","Id is required to Delete Data\nID: are present in ViewAll at menu");

                }
                else {
                    Integer deletedRows = mydb.deleteData(editTextId.getText().toString());
                    if (deletedRows > 0){
                        Toast.makeText(enterDetails.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                    showMessage("Sucess..!","Data Deleted Sucessfully");}
                    else{
                        Toast.makeText(enterDetails.this, "DataNot Deleted", Toast.LENGTH_SHORT).show();
                    showMessage("Failure..!","Failure to Delete Data\n\nPlease Enter the Valid credentials..?");}
                    clearAll();
                }
                break;

            case R.id.names:
                Cursor c=mydb.getAllData();
                if (c.getCount()==0) {
                    showMessage("Error..!", "No Names were Found!");
                    return true;
                }
                StringBuffer bufferr=new StringBuffer();
                while (c.moveToNext()){
                    bufferr.append("Name :"+c.getString(1)+"\n\n");
                }
                showMessage("Data",bufferr.toString());
                clearAll();

                break;

          /*  case R.id.viewid:
                Cursor res1=mydb.getAllData();
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
                    buffer.append("Colour :"+res1.getString(9)+"\n\n");
                   // buffer.append("Address :"+res1.getString(10)+"\n\n");

                }
                showMessage("SlamBook Details..", buffer.toString());
                break;*/


            case R.id.loginid:
                Intent i=new Intent(enterDetails.this,login.class);
                startActivity(i);
                break;
        }return true;
    }

    private void clearAll() {
        editName.setText("");
        editName.requestFocus();
        editNickname.setText("");
        editDateofbirth.setText("");
        editTextId.setText("");
        editEmail.setText("");
        editMobile.setText("");
        editachivement.setText("");
        editplace.setText("");
        editfood.setText("");
        editaddress.setText("");
     //   edithobbies.setText("");
     //   editaddress.setText("");
    }

}
