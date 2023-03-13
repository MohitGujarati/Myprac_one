package mohit.dev.myprac_one

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import mohit.dev.myprac_one.adapter.my_ListAdapter
import mohit.dev.myprac_one.adapter.my_ListAdapter.Onclickbtn
import mohit.dev.myprac_one.database.database
import mohit.dev.myprac_one.model.my_listModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var btnsave = findViewById<MaterialButton>(R.id.btnSave)
        var ed_name = findViewById<TextView>(R.id.edtext)
        var recdb = findViewById<RecyclerView>(R.id.rec_view)

        loadrec(recdb)

        btnsave.setOnClickListener {

            var dbhelper = database(this)

            dbhelper.insert(
                my_listModel(
                    it.id,
                    ed_name.text.toString()
                )
            )

            ed_name.text=""

            loadrec(recdb)

        }

    }

    private fun loadrec(recdb: RecyclerView) {
        recdb.layoutManager = LinearLayoutManager(this)
        var userlist: ArrayList<my_listModel>
        var dbRetrivehelper = database(this)
        userlist = dbRetrivehelper.retrive()


        var adapter = my_ListAdapter(this, userlist, object : my_ListAdapter.Onclickbtn {

            override fun onclickEditBtn(position: Int, name: String, id: Int) {
                Toast.makeText(this@MainActivity, "editbtn", Toast.LENGTH_SHORT).show()
            }

            override fun onclickDeleteBtn(position: Int, name: String, id: Int) {
                Toast.makeText(this@MainActivity, "Deletebtn", Toast.LENGTH_SHORT).show()

            }
        })

        recdb.adapter = adapter


    }


}