package com.symb.task.demux
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_home.*
import org.json.JSONArray
import java.io.IOException
class HomeActivity : AppCompatActivity() {
    private lateinit var questionRecyclerView: RecyclerView
    private lateinit var questionLayoutManager: RecyclerView.LayoutManager
    private var questionDataset=ArrayList<Question?>()
    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var categoryAdapter: RecyclerView.Adapter<*>
    private lateinit var categoryLayoutManager: RecyclerView.LayoutManager
    private var categoryDataset=ArrayList<Category>()
    private var categoryRecyclerviewVisibilityFlag=false
    private lateinit var questionAdapter:QuestionAdapter
    private var isLoading=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        PrepareCategoryDataset()
        populateQuestionData()
        questionLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        questionAdapter = QuestionAdapter(questionDataset)
        questionRecyclerView = findViewById<RecyclerView>(R.id.question_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = questionLayoutManager
            adapter = questionAdapter
        }
        initScrollListener()
        categoryLayoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        categoryAdapter=CategoryAdapter(this,categoryDataset,questionAdapter)
        categoryRecyclerView=findViewById<RecyclerView>(R.id.category_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager=categoryLayoutManager
            adapter=categoryAdapter
        }

        filter.setOnClickListener {
            if(categoryRecyclerviewVisibilityFlag==false)
            {
                categoryRecyclerView.visibility= View.VISIBLE
                categoryRecyclerviewVisibilityFlag=true
            }
            else{
                categoryRecyclerView.visibility= View.GONE
                categoryRecyclerviewVisibilityFlag=false
            }
        }
    }
    private fun populateQuestionData(){
        var begin=1
        var last=20
        PrepareQuestionDataset(begin,last)

    }
    private fun initScrollListener() {
        questionRecyclerView.addOnScrollListener(object:RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var linearLayoutManager=questionRecyclerView.layoutManager as LinearLayoutManager
                if(!isLoading){
                    if(linearLayoutManager!=null && linearLayoutManager.findLastCompletelyVisibleItemPosition()==questionDataset.size-1){
                        loadMore()
                        isLoading=true
                    }
                }
            }
        })
    }
     private fun loadMore(){
         questionDataset.add(null)
         questionAdapter.notifyItemInserted(questionDataset.size-1)
         var handler=Handler()
         handler.postDelayed(object:Runnable{
             override fun run() {
                 questionDataset.removeAt(questionDataset.size - 1)
                 var scrollPosition=questionDataset.size
                 questionAdapter.notifyItemRemoved(scrollPosition)
                 var currentSize=scrollPosition
                 var nextLimit=currentSize+5
                 PrepareQuestionDataset(currentSize,nextLimit)

                     questionAdapter.notifyDataSetChanged()
                 isLoading=false
             }
         },1000)
     }
    private fun PrepareCategoryDataset() {
        var jsonDataString = readJSONDatafromFile(R.raw.category)
        var jsonArray= JSONArray(jsonDataString)
        for (i in 1..jsonArray.length())
        {
            var itemobj=jsonArray.getJSONObject(i-1)
            for(names in itemobj.keys())
            {
                var items=itemobj.getJSONArray(names)
                var category=Category(names,items)
                categoryDataset.add(category)
            }
        }
    }
    private fun PrepareQuestionDataset(begin:Int,last:Int) {
        var limit=last
        var jsonDataString = readJSONDatafromFile(R.raw.question)
        var jsonArray= JSONArray(jsonDataString)
        if(limit>jsonArray.length())
            limit=jsonArray.length()
        for (i in begin..limit)
        {
            var itemobj=jsonArray.getJSONObject(i-1)
            var image=itemobj.getString("image")
            var college=itemobj.getString("college")
            var company=itemobj.getString("company")
            var frequency=itemobj.getString("frequency")
            var title=itemobj.getString("title")
            var description=itemobj.getString("description")
            var tags=itemobj.getJSONArray("tags")
            var resId=resources.getIdentifier(image,"drawable",packageName)
            var interview_type=itemobj.getString("interview type")
            var job_nature=itemobj.getString("job_nature")
            var question=Question(resId,company,college,
                Integer.parseInt(frequency),title,description,tags,interview_type,job_nature)
            questionDataset.add(question)
        }

    }
    private fun readJSONDatafromFile(res:Int): String {
        val json:String?
        try {
            val inputStream=resources.openRawResource(res)
            val size=inputStream.available()
            val buffer=ByteArray(size)
            val charset=Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json= String(buffer,charset)
        }catch (ex: IOException){
            ex.printStackTrace()
            return ""
        }
        return json
    }
}