package com.photocross

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavi : BottomNavigationView = findViewById(R.id.bottom_navigation_view)

        bottomNavi.setOnNavigationItemSelectedListener {
            menuItem ->
            when (menuItem.itemId) {
                R.id.action_cruces -> {
                    val fragment = CruceFragment.newInstance()
                    openFragment(fragment)
                    true
            }
                R.id.action_perfil -> {
                    val fragment = PerfilFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                else -> false
            }
        }
        bottomNavi.selectedItemId = R.id.action_cruces
      }

    private fun openFragment(fragment : Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
