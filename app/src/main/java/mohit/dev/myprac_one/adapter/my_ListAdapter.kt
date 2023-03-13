package mohit.dev.myprac_one.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mohit.dev.myprac_one.R
import mohit.dev.myprac_one.model.my_listModel

class my_ListAdapter(
    var context: Context,
    var userdatalist: List<my_listModel>,
    var onClickBtn: Onclickbtn
) : RecyclerView.Adapter<my_ListAdapter.ViewHolder>() {

    interface Onclickbtn {

        fun onclickEditBtn(position: Int, name: String, id: Int) {}
        fun onclickDeleteBtn(position: Int, name: String, id: Int) {}


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): my_ListAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_recview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: my_ListAdapter.ViewHolder, position: Int) {
        var mymodel = userdatalist[position]
        holder.txt_name.text = mymodel.name.toString()

        holder.btnedit.setOnClickListener {

            onClickBtn.onclickEditBtn(position, mymodel.name, mymodel.id)
        }

        holder.btnDelete.setOnClickListener {

            onClickBtn.onclickDeleteBtn(position, mymodel.name, mymodel.id)
        }


    }

    override fun getItemCount(): Int {
        return userdatalist.size
    }

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        var txt_name = itemview.findViewById<TextView>(R.id.txt_name)
        var btnedit = itemview.findViewById<Button>(R.id.btnedit)
        var btnDelete = itemview.findViewById<Button>(R.id.btndelete)

    }

}