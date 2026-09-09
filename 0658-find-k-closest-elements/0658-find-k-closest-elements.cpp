class Solution {
public:
    vector<int> findClosestElements(vector<int>& arr, int k, int x) {
        int n = arr.size();

        int closest_index = 0;
        int min_diff = INT_MAX;
        int left = 0, right = n - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int mid_val = arr[mid];
            int diff = abs(mid_val - x);

            if (mid_val < x) {
                left = mid + 1;
            } else if (mid_val > x) {
                right = mid - 1;
            } else {
                closest_index = mid;
                break;
            }
            if (diff < min_diff || (diff == min_diff && mid < closest_index)) {
                closest_index = mid;
                min_diff = diff;
            }
        }

        
        int left_p = closest_index - 1;
        int right_p = closest_index + 1;
        int count = 1;

        while (left_p >= 0 && right_p < n && count < k) {
            int diff_left = abs(arr[left_p] - x);
            int diff_right = abs(arr[right_p] - x);
            if (diff_left <= diff_right) {
                left_p--;
            } else {
                right_p++;
            }
            count++;
        }

        while (left_p >= 0 && count < k) {
            left_p--;
            count++;
        }

        left_p++;

        vector<int> ans(k);
        for (int i = 0; i < k; i++) {
            ans[i] = arr[left_p++];
        }

        return ans;
    }
};