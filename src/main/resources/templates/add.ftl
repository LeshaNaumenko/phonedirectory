<#import "wrapperHTML.ftl" as wrapper>
<@wrapper.page>

    <form action="/save" method="POST">
        <input name="username" type="text" placeholder="username" /><br><br>
        <input name="password" type="text" placeholder="password" /><br><br>
<#--        <div class="input_fields_wrap"><button class="add_field_button">Add More Numbers</button><br><br>-->
<#--            <div><input name="number[]" type="text" placeholder="number" /> <input name="company[]" type="text" placeholder="company" /></div>-->
<#--        </div>-->
        <input type="submit" value="Save">
    </form>
</@wrapper.page>