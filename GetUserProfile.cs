using System.Collections;
using UnityEngine;
using UnityEngine.Networking;

public class UserProfileRequester : MonoBehaviour
{
    private string serverUrl = "http://35.206.112.192:8080/api/users/";

    public void RequestUserProfile(string loginId)
    {
        StartCoroutine(GetUserProfile(loginId));
    }

    //GET 요청
    private IEnumerator GetUserProfile(string loginId)
    {
        string requestUrl = serverUrl + loginId;
        UnityWebRequest request = UnityWebRequest.Get(requestUrl);

        // HTTP 요청 전송
        yield return request.SendWebRequest();

        if (request.result == UnityWebRequest.Result.Success)
        {
            // 서버 응답 출력 (JSON 형식)
            string jsonResponse = request.downloadHandler.text;
            //Debug.Log("Response: " + jsonResponse);

            // JSON - DTO 파싱
            UserProfileDto userProfile = JsonUtility.FromJson<UserProfileDto>(jsonResponse);

           
           // Debug.Log($"Nickname: {userProfile.nickname}, Gender: {userProfile.gender}, Message: {userProfile.profileMessage}");
        }
        else
        {
            Debug.LogError($"Error: {request.error}");
        }
    }
}