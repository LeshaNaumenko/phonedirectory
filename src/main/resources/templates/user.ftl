<#import "wrapperHTML.ftl" as wrapper>
<@wrapper.page>
    <#if user??>
    <table class="table">
        <tbody>
        <tr>
            <th scope="row">ID</th>
            <td>${user.id}</td>
        </tr>
        <tr>
            <th scope="row">NAME</th>
            <td>${user.name}</td>
        </tr>
        <tr>
            <th scope="row">NUMBER</th>
            <td>
                <ul>
                    <#list user.phoneNumbers as phonenumber>
                        <li>${phonenumber.number} | ${phonenumber.companyName}</li>
                    </#list>
                </ul>

            </td>
        </tr>
        </tbody>
    </table>
    <#else>

        <h3> No such user. Try to upload a file with users by path:</h3>
        <h5>phonedirectory\src\main\resources\users.json</h5>
    </#if>
</@wrapper.page>