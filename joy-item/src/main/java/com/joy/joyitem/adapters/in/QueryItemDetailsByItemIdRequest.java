package com.joy.joyitem.adapters.in;

import java.util.List;
import java.util.UUID;

public record QueryItemDetailsByItemIdRequest(
        List<UUID> itemIds
) {
}
