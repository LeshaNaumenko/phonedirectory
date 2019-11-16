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
                <th scope="row">Mobile Account</th>
                <td>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">number</th>
                            <th scope="col">company</th>
                            <th scope="col">balance</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list user.userAccounts as useraccounts>
                            <tr>
                                <td>${useraccounts.phoneNumber.number}</td>
                                <td>${useraccounts.phoneNumber.companyName}</td>
                                <td>${useraccounts.cash} $</td>
                                <td>
                                    <form method="GET" action="/change">
                                        <input type="hidden" name="userAccountId" value="${useraccounts.id}"/>
                                        <input type="hidden" name="phoneNumberId" value="${useraccounts.phoneNumber.id}"/>
                                        <input type="text" name="number" placeholder="new number"/>
                                        <input type="text" name="company" placeholder="company name"/>
                                        <input class="btn btn-primary" type="submit" value="Change"/>
                                    </form>
                                </td>

                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </td>
            </tr>
            </tbody>
        </table>
    <#else>

        <h3> No such user. Try to upload a file with users by path:</h3>
        <h5>phonedirectory\src\main\resources\users.json</h5>
    </#if>
</@wrapper.page>