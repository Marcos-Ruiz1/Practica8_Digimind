package zazueta.marcos.mydigimind.ui.dashboard

import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import zazueta.marcos.mydigimind.R
import zazueta.marcos.mydigimind.databinding.FragmentDashboardBinding
import java.text.SimpleDateFormat
import zazueta.marcos.mydigimind.ui.Task
import zazueta.marcos.mydigimind.ui.home.HomeFragment

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val btn_time:Button = root.findViewById(R.id.btnTime)

        btn_time.setOnClickListener{
            val cal: Calendar = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{ timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                btn_time.text = SimpleDateFormat("HH:mm").format(cal.time)

            }
            TimePickerDialog(root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()

        }

        val btn_done: Button = root.findViewById(R.id.done)
        val name : EditText = root.findViewById(R.id.name)
        val checkMonday: CheckBox = root.findViewById(R.id.monday)
        val checkTuesday: CheckBox = root.findViewById(R.id.tuesday)
        val checkWednesday: CheckBox = root.findViewById(R.id.wednesday)
        val checkThursday: CheckBox = root.findViewById(R.id.thursday)
        val checkFriday: CheckBox = root.findViewById(R.id.friday)
        val checkSaturday: CheckBox = root.findViewById(R.id.Saturday)
        val checkSunday: CheckBox = root.findViewById(R.id.Sunday)

        btn_done.setOnClickListener {
            var title = name.text.toString()
            var time = btn_time.text.toString()
            var days = ArrayList<String>()

            if(checkMonday.isChecked){
                days.add("Monday")
            }
            if(checkTuesday.isChecked){
                days.add("Tuesday")
            }
            if(checkWednesday.isChecked){
                days.add("Wednesday")
            }
            if(checkThursday.isChecked){
                days.add("Thursday")
            }
            if(checkFriday.isChecked){
                days.add("Friday")
            }
            if(checkSaturday.isChecked){
                days.add("Saturday")
            }
            if(checkSunday.isChecked){
                days.add("Sunday")
            }

            var task = Task(title, days, time)
            HomeFragment.tasks.add(task)

            Toast.makeText(root.context, "task added ", Toast.LENGTH_SHORT).show()
        }


        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}