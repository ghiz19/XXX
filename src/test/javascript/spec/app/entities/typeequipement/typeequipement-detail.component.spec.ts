/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GmaovTestModule } from '../../../test.module';
import { TypeequipementDetailComponent } from 'app/entities/typeequipement/typeequipement-detail.component';
import { Typeequipement } from 'app/shared/model/typeequipement.model';

describe('Component Tests', () => {
    describe('Typeequipement Management Detail Component', () => {
        let comp: TypeequipementDetailComponent;
        let fixture: ComponentFixture<TypeequipementDetailComponent>;
        const route = ({ data: of({ typeequipement: new Typeequipement(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GmaovTestModule],
                declarations: [TypeequipementDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TypeequipementDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TypeequipementDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.typeequipement).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
