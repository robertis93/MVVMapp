package com.example.testtask.ui.employee_detail



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.testtask.R
import com.example.testtask.data_source.Repository
import com.example.testtask.TestTaskApp
import kotlinx.android.synthetic.main.employee_detail_fragment.*
import kotlinx.coroutines.launch


class EmployeeDetailFragment() : Fragment() {
    val viewModel: EmployeeDetailViewModel by viewModels{EmployeeDetailViewModelFactory(TestTaskApp.repository)}
    val args: EmployeeDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.employee_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch{
            val employee = viewModel.getEmployeeDetail(args.employeeId)!!
            name_textView.text = employee.firstName
            sec_name_textView.text = employee.lastName
            dateOfBirth_textView.text = employee.trueFormatDateOfBirth() 
            age_textView.text = employee.getAge().toString()
            speciality_textView.text = employee.getSpecialities()
        }
        /*viewModel.employeeLiveData.observe(viewLifecycleOwner, Observer {employee->
            name_textView.text = employee.firstName
            sec_name_textView.text = employee.lastName
            dateOfBirth_textView.text = employee.trueFormatDateOfBirth() 
            age_textView.text = employee.getAge().toString()
            speciality_textView.text = employee.getSpecialities()

         })*/
    }
}

@Suppress("UNCHECKED_CAST")
class EmployeeDetailViewModelFactory(val repository: Repository) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EmployeeDetailViewModel(repository) as T
    }
}