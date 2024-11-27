package ru.zatsoft.retrofit

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class PictureFragment : Fragment() {

    private var check = true

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_picture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar: Toolbar? = view.findViewById(R.id.toolbarMain)
        toolbar?.title = " "
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        val tvViewPager = view.findViewById<TextView>(R.id.tvViewPager)
        val ivPicture = view.findViewById<ImageView>(R.id.ivPicture)
        val tvAuthor = view.findViewById<TextView>(R.id.tvAuthor)
        val btnStart = view.findViewById<Button>(R.id.btnStart)
        val edCity = view.findViewById<EditText>(R.id.edCity)
        val viewPagerItem = arguments?.getParcelable<OnViewPagerModel>("picture")
        check = viewPagerItem?.check!!
        tvViewPager.text = viewPagerItem.name
        ivPicture.setImageResource(viewPagerItem.imageView)
        tvAuthor.text = viewPagerItem.author
        if(!check){
            btnStart.visibility = View.VISIBLE
            edCity.visibility = View.VISIBLE

            btnStart.setOnClickListener {
                val intent = Intent(activity, StartActivity::class.java)
                intent.putExtra("city", edCity.text.toString() )
                startActivity(intent)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu (menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.exit)
            activity?.finishAffinity()
        return super.onOptionsItemSelected(item)
    }
}