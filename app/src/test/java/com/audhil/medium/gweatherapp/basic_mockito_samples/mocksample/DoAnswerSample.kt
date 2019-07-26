package com.audhil.medium.gweatherapp.basic_mockito_samples.mocksample

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doAnswer


@RunWith(MockitoJUnitRunner::class)
class DoAnswerSample {

    //  based on sample: https://stackoverflow.com/questions/36615330/mockito-doanswer-vs-thenreturn/36627077
    //  normal - given, when, then
    @Mock
    lateinit var dummy: Dummy

    @Spy
    val list = mutableListOf<String>()

    @Test
    fun `normal testing`() {
        `when`(dummy.stringLength(anyString())).thenReturn(5)   //  preferred way for mocked objects
        val length = dummy.stringLength("get it break it")
        assertEquals("it is equal: good to go: ", 5, length)

        //  or
        doReturn(5).`when`(dummy).stringLength(anyString()) //  preferred way for spy objects
        val length2 = dummy.stringLength("get it break it")
        assertEquals("it is equal 2nd time: good to go: ", 5, length2)
    }

    @Test
    fun `using doAnswer`() {
        doAnswer { invocation ->
            (invocation.getArgument(0) as String).length * 2
        }.`when`(dummy).stringLength("get")

        assertEquals("it is equal 2nd time: good to go: ", 6, dummy.stringLength("get"))
    }


    //  based on sample: https://www.vogella.com/tutorials/Mockito/article.html#mockito_answers
//    @Test
//    fun answerTest() {
//        // with doAnswer():
//        doAnswer(returnsFirstArg<String>()).`when`(list).add(anyString())
//        // with thenAnswer():
//        `when`(list.add(anyString())).thenAnswer(returnsFirstArg<String>())
//        // with then() alias:
//        `when`(list.add(anyString())).then(returnsFirstArg<String>())
//    }

    /*
    * @Test
public final void callbackTest() {
    ApiService service = mock(ApiService.class);
    when(service.login(any(Callback.class))).thenAnswer(i -> {
        Callback callback = i.getArgument(0);
        callback.notify("Success");
        return null;
    });
}
*/
    /*
    * List<User> userMap = new ArrayList<>();
UserDao dao = mock(UserDao.class);
when(dao.save(any(User.class))).thenAnswer(i -> {
    User user = i.getArgument(0);
    userMap.add(user.getId(), user);
    return null;
});
when(dao.find(any(Integer.class))).thenAnswer(i -> {
    int id = i.getArgument(0);
    return userMap.get(id);
});
*/
}

open class Dummy {
    open fun stringLength(string: String): Int = string.length
}