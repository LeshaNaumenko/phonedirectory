<#import "wrapperHTML.ftl" as wrapper>
<@wrapper.page>

    <#if users?has_content>
        <table class="table ">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">User</th>
                <th scope="col">Number</th>
                <th scope="col">Company</th>
                <th scope="col">Balance</th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <th scope="row">${user.id}</th>
                    <td>${user.name}</td>
                    <td colspan="3">
                        <table class="table">
                            <tbody>
                            <#list user.userAccounts as useraccounts>
                            <tr>
                                <td>${useraccounts.phoneNumber.number}</td>
                                <td>${useraccounts.phoneNumber.companyName}</td>
                                <td>${useraccounts.cash} $</td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </td>
                    <td>
                        <a href="/delete?id=${user.id}">delete</a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    <#else>

        <h3> No users. Try to upload a file with users by path:</h3>
        <h5>phonedirectory\src\main\resources\users.json</h5>
    </#if>
</@wrapper.page>



