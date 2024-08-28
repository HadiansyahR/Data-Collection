package com.track_and_trace.restful_application.service.implement;

import com.track_and_trace.restful_application.entity.Product;
import com.track_and_trace.restful_application.entity.ProductDetail;
import com.track_and_trace.restful_application.entity.Uom;
import com.track_and_trace.restful_application.entity.UomPackage;
import com.track_and_trace.restful_application.model.request.CreateProductDetailRequest;
import com.track_and_trace.restful_application.model.request.UpdateProductDetailRequest;
import com.track_and_trace.restful_application.model.response.ProductDetailResponse;
import com.track_and_trace.restful_application.repository.ProductDetailRepository;
import com.track_and_trace.restful_application.repository.ProductRepository;
import com.track_and_trace.restful_application.repository.UomPackageRepository;
import com.track_and_trace.restful_application.repository.UomRepository;
import com.track_and_trace.restful_application.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UomRepository uomRepository;

    @Autowired
    private UomPackageRepository uomPackageRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    @Override
    public ProductDetailResponse create(CreateProductDetailRequest request) {
        validationService.validate(request);

        Product product = productRepository.findById(request.getIdProduct())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product is not found"));

        Uom uom = uomRepository.findById(request.getIdUom())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Uom is not found"));

        UomPackage uomPackage = uomPackageRepository.findById(request.getIdUomPackage())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Uom Package is not found"));

        ProductDetail productDetail = new ProductDetail();
        productDetail.setProduct(product);
        productDetail.setUom(uom);
        productDetail.setUomPackage(uomPackage);
        if (request.getGtin() != null)
            productDetail.setGtin(request.getGtin());
        if (request.getGtinoutbox() != null)
            productDetail.setGtinoutbox(request.getGtinoutbox());
        if (request.getGtinbasket() != null)
            productDetail.setGtinbasket(request.getGtinbasket());
        if (request.getGtinmasterbox() != null)
            productDetail.setGtinmasterbox(request.getGtinmasterbox());
        if (request.getGtinwrapping() != null)
            productDetail.setGtinwrapping(request.getGtinwrapping());
        if (request.getGtinbundling() != null)
            productDetail.setGtinbundling(request.getGtinbundling());
        if (request.getWrappingQty() != null)
            productDetail.setWrappingQty(request.getWrappingQty());
        if (request.getBundlingQty() != null)
            productDetail.setBundlingQty(request.getBundlingQty());
        if (request.getOutboxQty() != null)
            productDetail.setOutboxQty(request.getOutboxQty());
        if (request.getIsVial() != null)
            productDetail.setVial(request.getIsVial());
        if (request.getMinTemperature() != null)
            productDetail.setMinTemperature(request.getMinTemperature());
        if (request.getMaxTemperature() != null)
            productDetail.setMaxTemperature(request.getMaxTemperature());
        if (request.getDosis() != null)
            productDetail.setDosis(request.getDosis());
        if (request.getPenyuntikan() != null)
            productDetail.setPenyuntikan(request.getPenyuntikan());
        if (request.getJedaPenyuntikan() != null)
            productDetail.setJedaPenyuntikan(request.getJedaPenyuntikan());
        if (request.getIsSas() != null)
            productDetail.setSas(request.getIsSas());
        if (request.getBasketQty() != null)
            productDetail.setBasketQty(request.getBasketQty());
        if (request.getMasterboxQty() != null)
            productDetail.setMasterboxQty(request.getMasterboxQty());
        if (request.getLblsas() != null)
            productDetail.setLblsas(request.getLblsas());
        if (request.getKodesas() != null)
            productDetail.setKodesas(request.getKodesas());
        if (request.getNie() != null)
            productDetail.setNie(request.getNie());
        if (request.getLotno() != null)
            productDetail.setLotno(request.getLotno());
        if (request.getWeigthProduct() != null)
            productDetail.setWeigthProduct(request.getWeigthProduct());
        if (request.getIsAutoPrint() != null)
            productDetail.setAutoPrint(request.getIsAutoPrint());
        if (request.getProductflag() != null)
            productDetail.setProductflag(request.getProductflag());
        if (request.getProductType() != null)
            productDetail.setProductType(request.getProductType());
        if (request.getDistributionFlowType() != null)
            productDetail.setDistributionFlowType(request.getDistributionFlowType());
        if (request.getGtinType() != null)
            productDetail.setGtinType(request.getGtinType());
        if (request.getManufacturcountry() != null)
            productDetail.setManufacturcountry(request.getManufacturcountry());
        productDetailRepository.save(productDetail);

        return responseBuilder(productDetail);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ProductDetailResponse> findAll(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset/limit, limit);
        Page<ProductDetail> productDetails = productDetailRepository.findAll(pageable);
        List<ProductDetailResponse> productDetailResponses = productDetails.getContent().stream().map(this::responseBuilder).collect(Collectors.toList());

        return new PageImpl<>(productDetailResponses, pageable, productDetails.getTotalElements());
    }

    @Transactional(readOnly = true)
    @Override
    public ProductDetailResponse findById(int id) {
        ProductDetail productDetail = productDetailRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product detail is not found"));

        return responseBuilder(productDetail);
    }

    @Override
    public ProductDetailResponse findByProduct(int idProduct) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product is not found"));

        ProductDetail productDetail = productDetailRepository.findByProduct(product)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product detail is not found"));

        return responseBuilder(productDetail);
    }

    @Transactional
    @Override
    public void delete(int idProduct) {

        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product is not found"));

        ProductDetail productDetail = productDetailRepository.findByProduct(product)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product detail is not found"));

        productDetailRepository.delete(productDetail);
    }

    @Transactional
    @Override
    public ProductDetailResponse update(UpdateProductDetailRequest request) {
        validationService.validate(request);

        Product product = productRepository.findById(request.getIdProduct())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product is not found"));

        ProductDetail productDetail = productDetailRepository.findByProduct(product)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product detail is not found"));

        request.setIdProductDetail(productDetail.getIdProductDetail());

        if (request.getIdUom() != null){
            Uom uom = uomRepository.findById(request.getIdUom())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Uom is not found"));

            productDetail.setUom(uom);
        }

        if (request.getIdUomPackage() != null){
            UomPackage uomPackage = uomPackageRepository.findById(request.getIdUomPackage())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Uom Package is not found"));

            productDetail.setUomPackage(uomPackage);
        }

        if (request.getGtin() != null)
            productDetail.setGtin(request.getGtin());
        if (request.getGtinoutbox() != null)
            productDetail.setGtinoutbox(request.getGtinoutbox());
        if (request.getGtinbasket() != null)
            productDetail.setGtinbasket(request.getGtinbasket());
        if (request.getGtinmasterbox() != null)
            productDetail.setGtinmasterbox(request.getGtinmasterbox());
        if (request.getGtinwrapping() != null)
            productDetail.setGtinwrapping(request.getGtinwrapping());
        if (request.getGtinbundling() != null)
            productDetail.setGtinbundling(request.getGtinbundling());
        if (request.getWrappingQty() != null)
            productDetail.setWrappingQty(request.getWrappingQty());
        if (request.getBundlingQty() != null)
            productDetail.setBundlingQty(request.getBundlingQty());
        if (request.getOutboxQty() != null)
            productDetail.setOutboxQty(request.getOutboxQty());
        if (request.getIsVial() != null)
            productDetail.setVial(request.getIsVial());
        if (request.getMinTemperature() != null)
            productDetail.setMinTemperature(request.getMinTemperature());
        if (request.getMaxTemperature() != null)
            productDetail.setMaxTemperature(request.getMaxTemperature());
        if (request.getDosis() != null)
            productDetail.setDosis(request.getDosis());
        if (request.getPenyuntikan() != null)
            productDetail.setPenyuntikan(request.getPenyuntikan());
        if (request.getJedaPenyuntikan() != null)
            productDetail.setJedaPenyuntikan(request.getJedaPenyuntikan());
        if (request.getIsSas() != null)
            productDetail.setSas(request.getIsSas());
        if (request.getBasketQty() != null)
            productDetail.setBasketQty(request.getBasketQty());
        if (request.getMasterboxQty() != null)
            productDetail.setMasterboxQty(request.getMasterboxQty());
        if (request.getLblsas() != null)
            productDetail.setLblsas(request.getLblsas());
        if (request.getKodesas() != null)
            productDetail.setKodesas(request.getKodesas());
        if (request.getNie() != null)
            productDetail.setNie(request.getNie());
        if (request.getLotno() != null)
            productDetail.setLotno(request.getLotno());
        if (request.getWeigthProduct() != null)
            productDetail.setWeigthProduct(request.getWeigthProduct());
        if (request.getIsAutoPrint() != null)
            productDetail.setAutoPrint(request.getIsAutoPrint());
        if (request.getProductflag() != null)
            productDetail.setProductflag(request.getProductflag());
        if (request.getProductType() != null)
            productDetail.setProductType(request.getProductType());
        if (request.getDistributionFlowType() != null)
            productDetail.setDistributionFlowType(request.getDistributionFlowType());
        if (request.getGtinType() != null)
            productDetail.setGtinType(request.getGtinType());
        if (request.getManufacturcountry() != null)
            productDetail.setManufacturcountry(request.getManufacturcountry());

//        try {
//            productDetailRepository.save(productDetail);
//        } catch (Exception e) {
//            throw new DatabaseException("Failed to update product detail ", e);
//        }

        productDetailRepository.save(productDetail);

        return responseBuilder(productDetail);
    }

    @Override
    public ProductDetailResponse responseBuilder(ProductDetail entity) {
        return ProductDetailResponse
                .builder()
                .idProductDetail(entity.getIdProductDetail())
                .idProduct(entity.getProduct().getIdProduct())
                .productName(entity.getProduct().getProductName())
                .idUom(entity.getUom().getIdUom())
                .idUomPackage(entity.getUomPackage().getIdUomPackage())
                .gtin(entity.getGtin())
                .gtinoutbox(entity.getGtinoutbox())
                .gtinbasket(entity.getGtinbasket())
                .gtinmasterbox(entity.getGtinmasterbox())
                .gtinwrapping(entity.getGtinwrapping())
                .gtinbundling(entity.getGtinbundling())
                .wrappingQty(entity.getWrappingQty())
                .bundlingQty(entity.getBundlingQty())
                .outboxQty(entity.getOutboxQty())
                .isVial(entity.isVial())
                .minTemperature(entity.getMinTemperature())
                .maxTemperature(entity.getMaxTemperature())
                .dosis(entity.getDosis())
                .penyuntikan(entity.getPenyuntikan())
                .jedaPenyuntikan(entity.getJedaPenyuntikan())
                .isSas(entity.isSas())
                .basketQty(entity.getBasketQty())
                .masterboxQty(entity.getMasterboxQty())
                .lblsas(entity.getLblsas())
                .kodesas(entity.getKodesas())
                .nie(entity.getNie())
                .lotno(entity.getLotno())
                .weigthProduct(entity.getWeigthProduct())
                .isAutoPrint(entity.isAutoPrint())
                .productflag((entity.getProductflag()))
                .productType(entity.getProductType())
                .distributionFlowType(entity.getDistributionFlowType())
                .gtinType(entity.getGtinType())
                .manufacturcountry(entity.getManufacturcountry())
                .build();
    }
}
