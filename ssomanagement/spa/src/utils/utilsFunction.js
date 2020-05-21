import axios from 'axios';

export const getCSRFToken = function(){
    let axiosConfig = {
        headers : {},
        data:{},isAdminCheck
    };

    axiosConfig.headers['Accept'] = 'application/json';
    axiosConfig.headers['Content-Type'] = 'application/json';

    return axios.get('api/csrf-token', axiosConfig)
        .then(function (response) {
            console.log(response);
            axiosConfig.headers[response.data.csrf_header] = response.data.csrf_token;
            return axiosConfig;
        })
        .catch(function (error) {
            console.log(error);
            return null;
        });
};

export const isAdminCheck = function(roles){
    let i;
    for (i = 0; i< roles.length;i++){
        if (roles[i].authority == 'ROLE_ADMIN'){
            return true;
        }
    }
    return false;
};