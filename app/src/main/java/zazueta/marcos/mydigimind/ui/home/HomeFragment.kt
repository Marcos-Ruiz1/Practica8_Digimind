package zazueta.marcos.mydigimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import zazueta.marcos.mydigimind.R
import zazueta.marcos.mydigimind.databinding.FragmentHomeBinding
import zazueta.marcos.mydigimind.ui.Task

class HomeFragment : Fragment() {

    private var adaptador: AdaptadorTareas? = null
    private var _binding: FragmentHomeBinding? = null

    companion object{
        var tasks = ArrayList<Task>()
        var first = true
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if(first){
            fillTasks()
            first = false
        }

        adaptador = AdaptadorTareas(root.context, tasks)

        var gridView: GridView = root.findViewById(R.id.gridView)

        gridView.adapter = adaptador
        return root
    }

    fun fillTasks() {
        tasks.add(Task(title = "Practice 1", arrayListOf("Tuesday"), "17:30"))
        tasks.add(Task(title = "Practice 2", arrayListOf("Monday", "Sunday"), "17:00"))
        tasks.add(Task(title = "Practice 3", arrayListOf("Wednesday"), "14:00"))
        tasks.add(Task(title = "Practice 4", arrayListOf("Saturday"), "11:00"))
        tasks.add(Task(title = "Practice 5", arrayListOf("Friday"), "13:00"))
        tasks.add(Task(title = "Practice 6", arrayListOf("Thursday"), "10:40"))
        tasks.add(Task(title = "Practice 7", arrayListOf("Monday"),  "12:00"))
    }

    private class AdaptadorTareas: BaseAdapter{
        var tasks =  ArrayList<Task>()
        var contexto: Context? = null

        constructor(contexto: Context, tasks: ArrayList<Task>){
            this.tasks = tasks
            this.contexto = contexto
        }

        override fun getCount(): Int {
            return tasks.size
        }

        override fun getItem(position: Int): Any {
            return tasks[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var task = tasks[position]
            var inflador = LayoutInflater.from(contexto)
            var vista = inflador.inflate(R.layout.task_view, null)

            var tv_title: TextView = vista.findViewById(R.id.tv_title)
            var tv_days: TextView = vista.findViewById(R.id.tv_days)
            var tv_time: TextView = vista.findViewById(R.id.tv_time)

            tv_title.setText(task.title)
            tv_days.setText(task.dias.toString())
            tv_time.setText(task.tiempo)
            return vista

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}